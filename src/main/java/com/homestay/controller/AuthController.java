package com.homestay.controller;

import com.homestay.entity.User;
import com.homestay.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại.");
            return "register";
        }

        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("errorMessage", "Email đã được sử dụng.");
            return "register";
        }

        User user = User.builder()
            .username(username)
            .email(email)
            .password(passwordEncoder.encode(password))
            .role("USER")
            .build();

        userRepository.save(user);
        return "redirect:/login?registered";
    }
}
