package com.bms.bank_management_system.service;

import com.bms.bank_management_system.config.JwtTokenProvider;
import com.bms.bank_management_system.entity.User;
import com.bms.bank_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String signup(User user) {
        // Prevent duplicate account registration
        if (userRepository.existsById(user.getAccountNumber())) {
            return "User already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String login(String accountNumber, String pin) {
        try {
            System.out.println("Attempting login for account: " + accountNumber);

            Optional<User> userOptional = userRepository.findById(accountNumber);
            
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                System.out.println("User found! Validating PIN...");

                if (((String) user.getPin()).trim().equals(pin.trim())) {
                    System.out.println("Login successful!");
                    return jwtTokenProvider.generateToken(accountNumber);
                } else {
                    System.out.println("Invalid PIN entered!");
                }
            } else {
                System.out.println("User not found!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print full error stack trace
        }

        return null;
    }
}
