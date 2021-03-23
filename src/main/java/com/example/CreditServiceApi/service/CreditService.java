package com.example.CreditServiceApi.service;

import com.example.CreditServiceApi.domain.mainEntity.Budget;
import com.example.CreditServiceApi.domain.mainEntity.Credit;
import com.example.CreditServiceApi.repo.BudgetRepo;
import com.example.CreditServiceApi.repo.CreditRepo;
import com.example.CreditServiceApi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CreditService {

    private final UserRepo userRepo;
    private final CreditRepo creditRepo;
    private final BudgetRepo budgetRepo;

    @Autowired
    public CreditService(UserRepo userRepo, CreditRepo creditRepo, BudgetRepo budgetRepo ){
        this.userRepo = userRepo;
        this.creditRepo = creditRepo;
        this.budgetRepo = budgetRepo;
    }

    // TODO exception
    // TODO "not enough money"
    public Optional<Credit> saveCredit(Credit credit, Long userId) {
        Budget budget = budgetRepo.findOneByBudgetId(1L);
        credit.setStartDate(LocalDateTime.now());
        credit.setSumPay();
        credit.setFinishDate(credit.getStartDate().plusMonths(credit.getPeriodMonths()));
        credit.setMonthSum(credit.getSumPay()/credit.getPeriodMonths());
        credit.setSumPaid(0L);
        credit.setSumLeft(credit.getSumPay());
        if (!budget.createCredit(credit.getSumTake()))
            return null;
        budgetRepo.save(budget);
        return userRepo.findById(userId).map(user -> {
            credit.setUserOwner(user);
            return creditRepo.save(credit);
        });
    }

    public Optional<Credit> payment(Long creditId, int monthsNumber){
        return creditRepo.findById(creditId).map(credit -> {
            Long money = credit.getMonthSum() * monthsNumber;
            credit.setSumPaid(credit.getSumPaid() + money);
            credit.setSumLeft(credit.getSumLeft() - money);
            budgetRepo.findOneByBudgetId(1L).payBackSum(money);
            if (credit.getSumLeft() <= 0)
                credit.setPaidOff(true);
            return creditRepo.save(credit);
        });
    }

}
