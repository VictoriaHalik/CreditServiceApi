package com.example.CreditServiceApi.controllers;

import com.example.CreditServiceApi.config.JwtProvider;
import com.example.CreditServiceApi.domain.additionalEntity.AuthRequest;
import com.example.CreditServiceApi.domain.additionalEntity.AuthResponse;
import com.example.CreditServiceApi.domain.mainEntity.User;
import com.example.CreditServiceApi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/register_admin")
    public User registerAdmin(@RequestBody User user) {
        return userService.saveAdmin(user);
    }

    @PostMapping("/login")
    public AuthResponse authUser(@RequestBody AuthRequest request) {
        User user = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

}