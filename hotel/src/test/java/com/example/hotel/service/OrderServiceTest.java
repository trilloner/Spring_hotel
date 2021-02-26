package com.example.hotel.service;

import com.example.hotel.dto.ReservationDTO;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.models.Reservation;
import com.example.hotel.models.User;
import com.example.hotel.models.UserDetailsImpl;
import com.example.hotel.repositories.OrderRepository;
import com.example.hotel.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;


    @Autowired
    private OrderService orderService;


    @Test
    public void getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        when(orderRepository.findAllByOrderByStatusDesc()).thenReturn(list);
        Assertions.assertEquals(list, orderService.getAllOrders());
        verify(orderRepository, times(1)).findAllByOrderByStatusDesc();
    }

    @Test
    public void getAllReservationsByUser() {
        List<Reservation> list = new ArrayList<>();
        User user = new User();
        user.setName("Ivan");
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        when(orderRepository.getAllByUserByUserId(any())).thenReturn(list);

        Assertions.assertEquals(list, orderService.getAllOrdersByUser(userDetails));

        verify(orderRepository, times(1)).getAllByUserByUserId(user);
    }

    @Test
    public void createNewReservationByUser() throws ResourceNotFoundException {
        Reservation reservation = new Reservation();
        ReservationDTO reservationDTO = new ReservationDTO();
        User user = new User();
        user.setName("Ivan");
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        when(orderRepository.save(any())).thenReturn(reservation);

        Assertions.assertEquals(reservation, orderService.createOrder(userDetails, reservationDTO));

        verify(orderRepository, times(1)).save(any());
    }
}
