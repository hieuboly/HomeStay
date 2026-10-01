package com.homestay.controller;

import com.homestay.repository.BookingRepository;
import com.homestay.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public ProfileController(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        var user = userRepository.findByUsername(authentication.getName())
            .orElseThrow(() -> new IllegalStateException("Authenticated user was not found"));

        model.addAttribute("user", user);
        model.addAttribute("bookings", bookingRepository.findByUser_UsernameOrderByCheckInDateDesc(user.getUsername()));
        return "profile";
    }
}
