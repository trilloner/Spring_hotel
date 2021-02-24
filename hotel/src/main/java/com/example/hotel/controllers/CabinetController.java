package com.example.hotel.controllers;

import com.example.hotel.models.UserDetailsImpl;
import com.example.hotel.services.OrderService;
import com.example.hotel.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class CabinetController {

    private final UserService userService;
    private final OrderService orderService;

    public CabinetController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping("/cabinet")
    public String getCabinetPage(Model model,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        model.addAttribute("orders", orderService.getAllOrdersByUser(userDetails));
        return "cabinet";
    }
}
