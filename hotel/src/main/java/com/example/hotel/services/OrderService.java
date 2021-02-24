package com.example.hotel.services;

import com.example.hotel.dto.OrderDto;
import com.example.hotel.dto.ReservationDTO;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.mapper.ReservationMapper;
import com.example.hotel.models.*;
import com.example.hotel.repositories.OrderRepository;
import com.example.hotel.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ReservationMapper reservationMapper;
    private final RoomRepository roomRepository;

    public OrderService(OrderRepository orderRepository, UserService userService,
                        ReservationMapper reservationMapper, RoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
        this.roomRepository = roomRepository;
    }

    public Reservation createOrder(UserDetailsImpl user, ReservationDTO reservationDTO)
            throws ResourceNotFoundException {
        try {
            return orderRepository.save(
                    reservationMapper.toEntity(reservationDTO)
                            .addStatusAndUser(Status.PENDING, user.getUser())
            );
        } catch (Exception e) {
            log.error("User cannot create order");
            throw new ResourceNotFoundException("Something went wrong. Try again");
        }
    }

    public List<Reservation> getAllOrders() {
        return orderRepository.findAllByOrderByStatusDesc();
    }

    public List<Reservation> getAllOrdersByUser(UserDetailsImpl userDetails) {
        return orderRepository.getAllByUserByUserId(userDetails.getUser());
    }

    @Transactional
    public Reservation updateOrder(Long id, OrderDto orderDto) throws ResourceNotFoundException {
        try {
            Reservation reservation = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Can`t find user by id"));
            Room room = roomRepository.findById(orderDto.getRoomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Can`t find room by id"));
            return orderRepository.save(reservation.addStatusAndRoom(Status.CONFIRMED, room));
        } catch (Exception e) {
            log.info("Can`t update user");
            throw new ResourceNotFoundException("Can`t update order");
        }
    }
}
