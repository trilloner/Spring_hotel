package com.example.hotel.controllers;

import com.example.hotel.models.UserDetailsImpl;
import com.example.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for user`s cabinet
 */
@Controller
@Slf4j
public class CabinetController {

    private final OrderService orderService;

    public CabinetController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Returns the user`s cabinet
     *
     * @param model       page model
     * @param userDetails current user in session
     * @return cabinet page
     */
    @GetMapping("/cabinet")
    public String getCabinetPage(Model model,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        model.addAttribute("orders", orderService.getAllOrdersByUser(userDetails));
        return "cabinet";
    }
}
