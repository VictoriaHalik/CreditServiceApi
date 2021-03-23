package com.example.CreditServiceApi.repo;

import com.example.CreditServiceApi.domain.mainEntity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, Long> {
    Budget findOneByBudgetId(Long budgetId);
}
