package com.example.CreditServiceApi.controllers;

import com.example.CreditServiceApi.domain.Credit;
import com.example.CreditServiceApi.domain.User;
import com.example.CreditServiceApi.domain.Views;
import com.example.CreditServiceApi.repo.UserRepo;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo userRepo;
    private final CreditRepo creditRepo;

    @Autowired
    public UserController(UserRepo userRepo, CreditRepo creditRepo) {
        this.userRepo = userRepo;
        this.creditRepo = creditRepo;
    }

    // works fits
    // TODO exception of user which doesnt exist
    @GetMapping("{id}")
    @JsonView({Views.UserInfo.class})
    public User getOneUser(@PathVariable("id") User user) {
        return user;
    }

    // works fits
    @GetMapping
    @JsonView({Views.UserInfo.class})
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    // works fits
    @GetMapping("{userId}/credits")
    public List<Credit> getAllCreditsByUserId(@PathVariable (value = "userId") Long userId){
        User currUser = userRepo.findByUserId(userId);
        return creditRepo.findByUserOwner(currUser);
    }

    // works fits
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

}
