package com.example.hotel.controllers;

import com.example.hotel.dto.ReservationDTO;
import com.example.hotel.models.UserDetailsImpl;
import com.example.hotel.services.OrderService;
import com.example.hotel.services.UserService;
import com.example.hotel.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Reservation controller class
 */
@Controller
@Slf4j
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * Returns reservation page
     *
     * @param model page model
     * @return reservation page
     */
    @GetMapping("/order")
    public String getOrderPage(Model model) {
        model.addAttribute("order", new ReservationDTO());
        return "order";
    }

    /**
     * Adds new reservation created by user.
     *
     * @param reservation   valid reservation dto
     * @param bindingResult binding result
     * @param user          current user in active session
     * @param model         page model
     * @return redirect to cabinet page
     */
    @PostMapping("/order")
    public String createNewOrder(@ModelAttribute("order")
                                 @Valid ReservationDTO reservation,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal UserDetailsImpl user,
                                 Model model) {
        try {
            ValidatorUtils.validateReservation(bindingResult);
            orderService.createOrder(user, reservation);
        } catch (Exception e) {
            log.error("Cannot create order");
            model.addAttribute("order", reservation);
            model.addAttribute("errorMessage", e.getMessage());
            return "order";
        }
        return "redirect:/cabinet";
    }
}
