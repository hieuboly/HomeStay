package com.homestay.controller;

import com.homestay.entity.Booking;
import com.homestay.entity.Room;
import com.homestay.entity.User;
import com.homestay.repository.UserRepository;
import com.homestay.service.BookingService;
import com.homestay.service.RoomService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class BookingController {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(RoomService roomService, BookingService bookingService, UserRepository userRepository) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/booking/{roomId}")
    public String bookingForm(@PathVariable Long roomId, Model model) {
        model.addAttribute("room", roomService.getRoomById(roomId));
        model.addAttribute("booking", new Booking());
        return "booking-form";
    }

    @PostMapping("/booking/{roomId}")
    public String submitBooking(@PathVariable Long roomId,
                                @RequestParam String checkInDate,
                                @RequestParam String checkOutDate,
                                @RequestParam Integer guestCount,
                                @RequestParam String customerName,
                                @RequestParam String customerPhone,
                                @RequestParam(required = false) String notes,
                                Authentication authentication,
                                Model model) {
        Room room = roomService.getRoomById(roomId);
        User user = null;
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            user = userRepository.findByUsername(authentication.getName()).orElse(null);
        }

        try {
            Booking booking = bookingService.createBooking(
                room,
                user,
                LocalDate.parse(checkInDate),
                LocalDate.parse(checkOutDate),
                guestCount,
                customerName,
                customerPhone,
                notes
            );
            return "redirect:/booking/success/" + booking.getId();
        } catch (IllegalArgumentException ex) {
            model.addAttribute("room", room);
            model.addAttribute("errorMessage", ex.getMessage());
            return "booking-form";
        }
    }

    @GetMapping("/booking/success/{bookingId}")
    public String bookingSuccess(@PathVariable Long bookingId, Model model) {
        model.addAttribute("booking", bookingService.getAllBookings().stream()
            .filter(b -> b.getId().equals(bookingId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Booking not found")));
        return "booking-success";
    }
}
