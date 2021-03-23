package com.example.CreditServiceApi.domain.mainEntity;

import com.example.CreditServiceApi.domain.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Budgets")
public class Budget {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.BudgetInfo.class)
    private Long budgetId;

    @NotNull
    @JsonView(Views.BudgetInfo.class)
    private boolean isEmpty = false;

    @NotNull
    @JsonView(Views.BudgetInfo.class)
    private Long availableSum;

    public boolean createCredit(Long sum){
        if(this.availableSum < sum )
            return false;
        this.availableSum -= sum;
        return true;
    }

    public void payBackSum(Long sum){
        this.availableSum += sum;
    }

    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }

}

