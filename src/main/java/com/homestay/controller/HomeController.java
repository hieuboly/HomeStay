package com.homestay.controller;

import com.homestay.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final RoomService roomService;

    public HomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("featuredRooms", roomService.getFeaturedRooms());
        return "index";
    }
}
