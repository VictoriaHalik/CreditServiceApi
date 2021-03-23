package com.example.CreditServiceApi.controllers;

import com.example.CreditServiceApi.config.CustomUserDetails;
import com.example.CreditServiceApi.domain.mainEntity.Credit;
import com.example.CreditServiceApi.domain.mainEntity.User;
import com.example.CreditServiceApi.domain.Views;
import com.example.CreditServiceApi.repo.RoleRepo;
import com.example.CreditServiceApi.repo.UserRepo;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.example.CreditServiceApi.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo userRepo;
    private final CreditRepo creditRepo;
    private final RoleRepo roleRepo;
    private final UserService userService;

    @Autowired
    public UserController(UserRepo userRepo, CreditRepo creditRepo, RoleRepo roleRepo, UserService userService) {
        this.userRepo = userRepo;
        this.creditRepo = creditRepo;
        this.roleRepo = roleRepo;
        this.userService = userService;
    }

    // works fits
    // TODO exception of user which doesnt exist
    @GetMapping("/user")
    @JsonView({Views.UserInfo.class})
    public User getOneUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userRepo.findOneByUserId(userDetails.getUserId());
    }

    // works fits
    @GetMapping("/list")
    @JsonView({Views.UserInfo.class})
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    // works fits
    @GetMapping("/list_admins")
    @JsonView({Views.UserInfo.class})
    public List<User> allAdmins() {
        return userRepo.findAllByRole(roleRepo.findByName("ROLE_ADMIN"));
    }

    // works fits
    @GetMapping("user/credits")
    @JsonView(Views.CreditInfo.class)
    public List<Credit> getAllCreditsByUserId(@AuthenticationPrincipal CustomUserDetails userDetails){
        User currUser = userRepo.findOneByUserId(userDetails.getUserId());
        return creditRepo.findByUserOwner(currUser);
    }

    // works fits
    @PutMapping("/user/edit")
    @JsonView(Views.UserInfo.class)
    public Optional<User> editUser(@RequestBody User newUser, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.userEdit(newUser, userDetails.getUserId());
    }

    @DeleteMapping("/user/delete")
    boolean deleteEmployee(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userService.deleteUser(userDetails.getUserId());
    }

}
