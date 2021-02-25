package com.example.hotel.dto;

import lombok.Data;

/**
 * Data transfer object to order
 */
@Data
public class OrderDto {
    public Long id;
    public Long roomId;
}
