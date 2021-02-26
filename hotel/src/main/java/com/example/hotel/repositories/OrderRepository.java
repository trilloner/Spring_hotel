package com.example.hotel.repositories;

import com.example.hotel.models.Reservation;
import com.example.hotel.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByUserByUserId(User userByUserId);

    List<Reservation> findAllByOrderByStatusDesc();

}
