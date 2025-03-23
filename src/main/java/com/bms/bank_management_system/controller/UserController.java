package com.bms.bank_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.bank_management_system.entity.User;
import com.bms.bank_management_system.payload.LoginRequest;
import com.bms.bank_management_system.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // User signup
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userService.signup(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest.getAccountNumber(), loginRequest.getPin());
        return token != null ? ResponseEntity.ok(token) : ResponseEntity.status(401).body("Invalid credentials!");
    }
}
