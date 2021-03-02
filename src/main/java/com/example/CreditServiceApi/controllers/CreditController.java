package com.example.CreditServiceApi.controllers;

import com.example.CreditServiceApi.domain.Credit;
import com.example.CreditServiceApi.domain.Views;
import com.example.CreditServiceApi.repo.UserRepo;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("credits")
public class CreditController {
    private final UserRepo userRepo;
    private final CreditRepo creditRepo;

    @Autowired
    public CreditController(UserRepo userRepo, CreditRepo creditRepo) {
        this.userRepo = userRepo;
        this.creditRepo = creditRepo;
    }

    // worksF
    @GetMapping
    @JsonView(Views.CreditInfo.class)
    public List<Credit> allCredits() {
        return creditRepo.findAll();
    }

    // works
    @GetMapping("{creditId}")
    @JsonView(Views.CreditInfo.class)
    public Credit getOneCreditByCreditId(@PathVariable("creditId") Credit credit) {
        return credit;
    }

    // works
    @PostMapping("{userId}/create")
    public Credit createCredit(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Credit credit) {
        credit.setStartDate(LocalDateTime.now());
        return userRepo.findById(userId).map(user -> {
            credit.setUserOwner(user);
            return creditRepo.save(credit);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

}