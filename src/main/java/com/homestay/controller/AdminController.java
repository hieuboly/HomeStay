package com.homestay.controller;

import com.homestay.entity.Room;
import com.homestay.service.BookingService;
import com.homestay.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoomService roomService;
    private final BookingService bookingService;

    public AdminController(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin";
    }

    @PostMapping("/rooms")
    public String createRoom(@RequestParam String title,
                             @RequestParam String city,
                             @RequestParam BigDecimal pricePerNight,
                             @RequestParam Integer maxGuests,
                             @RequestParam Integer bedrooms,
                             @RequestParam Integer bathrooms,
                             @RequestParam Integer rating,
                             @RequestParam String description,
                             @RequestParam String imageUrl) {
        Room room = Room.builder()
            .title(title)
            .city(city)
            .pricePerNight(pricePerNight)
            .maxGuests(maxGuests)
            .bedrooms(bedrooms)
            .bathrooms(bathrooms)
            .rating(rating)
            .description(description)
            .imageUrl(imageUrl)
            .active(true)
            .build();

        roomService.save(room);
        return "redirect:/admin";
    }
}
