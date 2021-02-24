package com.example.hotel.controllers;


import com.example.hotel.dto.OrderDto;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.services.OrderService;
import com.example.hotel.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AdminController {
    private final OrderService orderService;
    private final RoomService roomService;

    public AdminController(OrderService orderService, RoomService roomService) {
        this.orderService = orderService;
        this.roomService = roomService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("orderRoom", new OrderDto());
        return "admin";
    }

    @PostMapping("/admin/{orderId}")
    public String updateOrder(@PathVariable("orderId") Long orderId, OrderDto orderDto) {
        try {
            orderService.updateOrder(orderId, orderDto);
        } catch (ResourceNotFoundException e) {
            log.error("{} Can`t update order", e.getMessage());
        }
        return "redirect:/admin";
    }


}
