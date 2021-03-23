package com.example.CreditServiceApi.domain.additionalEntity;

import lombok.Data;

@Data
public class PaymentRequest {
    private String password;
    private int monthsNumber;

    public int getMonthsNumber() {
        return monthsNumber;
    }

    public String getPassword() {
        return password;
    }
}
