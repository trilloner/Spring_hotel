package com.example.hotel.utils;

import com.example.hotel.exceptions.ValidationException;
import org.springframework.validation.Errors;

public class ValidatorUtils {

    public static void validateReservation(Errors errors)
            throws ValidationException {
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getFieldErrors()
                    .get(0)
                    .getDefaultMessage()
            );
        }
    }
}
