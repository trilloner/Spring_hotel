package com.example.hotel.services;

import com.example.hotel.dto.RoomsDto;
import com.example.hotel.exceptions.ResourceNotFoundException;
import com.example.hotel.models.Room;
import com.example.hotel.repositories.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomsDto getAllRooms() {
        return new RoomsDto(roomRepository.findAll());
    }

    public Page<Room> findPaginated(int pageNum, String sortField, String sortDir) {
        return roomRepository.findAll(PageRequest.of(pageNum - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                                      : Sort.by(sortField).descending()));
    }

    public Room findByRoomId(Long id) throws ResourceNotFoundException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find user by id"));
    }

}
