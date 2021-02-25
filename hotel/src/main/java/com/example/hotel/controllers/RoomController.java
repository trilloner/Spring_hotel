package com.example.hotel.controllers;

import com.example.hotel.dto.ReservationDTO;
import com.example.hotel.models.Room;
import com.example.hotel.services.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Room controller class
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final int START_PAGE = 1;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Returns a paginated output of all rooms
     *
     * @param model page model
     * @return list of rooms
     */
    @GetMapping()
    public String findAllRooms(Model model) {
        model.addAttribute("reservation", new ReservationDTO());
        return findPaginated(START_PAGE, "price", "asc", model);
    }

    /**
     * Returns page of rooms
     *
     * @param pageNum   page number
     * @param field     sort field
     * @param direction sort direction
     * @param model     page model
     * @return room page
     */
    @GetMapping("/{pageNum}")
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

    //TODO
    @PostMapping("/booking")
    public String bookingRoom(ReservationDTO reservationDTO) {
        System.out.println("or");
        return "redirect:/cabinet";
    }
}
