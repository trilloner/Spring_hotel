package com.example.hotel.mapper;

import com.example.hotel.dto.ReservationDTO;
import com.example.hotel.models.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ReservationMapper {

    Reservation toEntity(ReservationDTO reservationDTO);
}
