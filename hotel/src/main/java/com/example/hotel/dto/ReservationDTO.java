package com.example.hotel.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Data transfer object for creating reservation
 */
@Data
public class ReservationDTO {
    @NotNull(message = "this field is required")
    @Min(1)
    private Integer numberOfSeats;
    @NotBlank(message = " Please fill this")
    private String apartments;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @NotNull
    private LocalDate checkIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull
    private LocalDate checkOut;

}
