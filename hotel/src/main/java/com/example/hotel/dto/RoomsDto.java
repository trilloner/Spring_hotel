package com.example.hotel.dto;

import com.example.hotel.models.Room;
import lombok.Data;

import java.util.List;

/**
 * Data transfer object for displaying rooms
 */
@Data
public class RoomsDto {
    public List<Room> rooms;

    public RoomsDto(List<Room> rooms) {
        this.rooms = rooms;
    }
}
