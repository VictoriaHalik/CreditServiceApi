package com.example.CreditServiceApi.controllers;

import com.example.CreditServiceApi.config.CustomUserDetails;
import com.example.CreditServiceApi.domain.mainEntity.Budget;
import com.example.CreditServiceApi.domain.mainEntity.Credit;
import com.example.CreditServiceApi.domain.additionalEntity.PaymentRequest;
import com.example.CreditServiceApi.domain.Views;
import com.example.CreditServiceApi.repo.BudgetRepo;
import com.example.CreditServiceApi.repo.UserRepo;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.example.CreditServiceApi.service.CreditService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("credits")
public class CreditController {
    private final UserRepo userRepo;
    private final BudgetRepo budgetRepo;
    private final CreditRepo creditRepo;
    private final CreditService creditService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreditController(BudgetRepo budgetRepo,UserRepo userRepo, CreditRepo creditRepo, CreditService creditService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.creditRepo = creditRepo;
        this.creditService = creditService;
        this.passwordEncoder = passwordEncoder;
        this.budgetRepo = budgetRepo;
    }

    // works
    @GetMapping("/list")
    @JsonView(Views.CreditInfo.class)
    public List<Credit> allCredits() {
        return creditRepo.findAll();
    }

    // works
    @GetMapping("{creditId}")
    @JsonView(Views.CreditInfo.class)
    public Credit getOneCreditByCreditId(@PathVariable("creditId") Credit credit,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (!credit.getUserOwner().getUserId().equals(userDetails.getUserId())){
            return null;
        }
        return credit;
    }

    // works
    @PostMapping("/create")
    @JsonView(Views.CreditInfo.class)
    public Optional<Credit> createCredit(@Valid @RequestBody Credit credit,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        return creditService.saveCredit(credit, userDetails.getUserId());
    }

    // works
    // TODO return error
    @PutMapping("/payback/{creditId}")
    @JsonView(Views.CreditInfo.class)
    public Optional<Credit> payback(@Valid @RequestBody PaymentRequest paymentRequest,
                                    @PathVariable Long creditId,
                                    @AuthenticationPrincipal CustomUserDetails userDetails){
        if (creditRepo.findOneById(creditId).getUserOwner().getUserId().equals(userDetails.getUserId()) &&
                passwordEncoder.matches(paymentRequest.getPassword(), userRepo.findOneByUserId(userDetails.getUserId()).getPassword()))
            return creditService.payment(creditId, paymentRequest.getMonthsNumber());
        return null;
    }

    @GetMapping("/budget_state")
    @JsonView(Views.BudgetInfo.class)
    public Budget budgetStateInfo(){
        return budgetRepo.findOneByBudgetId(1L);
    }

}