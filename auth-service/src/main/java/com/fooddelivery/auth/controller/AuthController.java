package com.fooddelivery.auth.controller;

import com.fooddelivery.auth.config.JwtUtil;
import com.fooddelivery.auth.model.User;
import com.fooddelivery.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("error", "username already exists"));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(body.get("password")));
        user.setEmail(body.get("email"));
        user.setRole(body.getOrDefault("role", "CUSTOMER"));
        userRepository.save(user);
        return ResponseEntity.status(201).body(Map.of("message", "registered", "username", username));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        return userRepository.findByUsername(body.get("username"))
                .filter(u -> encoder.matches(body.get("password"), u.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of(
                        "token", jwtUtil.generateToken(u.getUsername(), u.getRole()),
                        "role", u.getRole()
                )))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "invalid credentials")));
    }
}
