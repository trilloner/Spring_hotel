package com.example.hotel.controllers;

import com.example.hotel.models.Room;
import com.example.hotel.services.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String findAllRooms(Model model) {
        return findPaginated(1, "price", "asc", model);
    }

    @GetMapping("/rooms/{pageNum}")
    public String findPaginated(@PathVariable int pageNum,
                                @Param("field") String field,
                                @Param("direction") String direction,
                                Model model) {
        Page<Room> page = roomService.findPaginated(pageNum, field, direction);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("page", page);
        model.addAttribute("sortField", field);
        model.addAttribute("sortDir", direction);

        return "rooms";
    }
}
