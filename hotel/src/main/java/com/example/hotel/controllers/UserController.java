package com.example.hotel.controllers;

import com.example.hotel.dto.UserRegistrationDto;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.exceptions.ValidationException;
import com.example.hotel.services.UserService;
import com.example.hotel.utils.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * User controller class
 */
@Slf4j
@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register new user to service
     *
     * @param userDto       user data
     * @param bindingResult binding result
     * @param model         page model
     * @return redirect to login
     */
    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult bindingResult,
                                  Model model) {
        try {
            ValidatorUtils.validateReservation(bindingResult);
            userService.registerNewUser(userDto);
        } catch (ResourceNotFoundException | ValidationException e) {
            log.info("User registration failed");
            model.addAttribute("user", userDto);
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }

    /**
     * Returns main page
     *
     * @return main page
     */
    @GetMapping("/")
    public String getMainPage() {
        return "main";
    }

    /**
     * Returns login page
     *
     * @param model page model
     * @return registration page
     */
    @GetMapping("/registration")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

}
