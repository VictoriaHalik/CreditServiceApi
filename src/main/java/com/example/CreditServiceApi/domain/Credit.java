package com.example.CreditServiceApi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="Credits")
public class Credit {
    static final private int percent = 13;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.CreditInfo.class)
    private Long id;

    @NotNull
    @JsonView(Views.CreditInfo.class)
    private Long sumTake;
    @NotNull
    @JsonView(Views.CreditInfo.class)
    private int periodMonths;

    @JsonView(Views.CreditInfo.class)
    private Long sumPay;

    @JsonView(Views.CreditInfo.class)
    private boolean paidOff = false;

    @JsonView(Views.CreditInfo.class)
    private Long monthSum;

    @JsonView(Views.CreditInfo.class)
    private Long sumPaid;

    @JsonView(Views.CreditInfo.class)
    private Long sumLeft;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.CreditInfo.class)
    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonView(Views.CreditInfo.class)
    private User userOwner;

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public Long getId() {
        return id;
    }

    public static int getPercent() {
        return percent;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Long getSumLeft() {
        return sumLeft;
    }

    public void setSumLeft(Long sumLeft) {
        this.sumLeft = sumLeft;
    }

    public Long getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(Long sumPaid) {
        this.sumPaid = sumPaid;
    }

    public Long getMonthSum() {
        return monthSum;
    }

    public void setMonthSum(Long monthSum) {
        this.monthSum = monthSum;
    }

    public boolean isPaidOff() {
        return paidOff;
    }

    public void setPaidOff(boolean paidOff) {
        this.paidOff = paidOff;
    }

    public Long getSumPay() {
        return sumPay;
    }

    public void setSumPay(Long sumPay) {
        this.sumPay = sumPay;
    }

    public int getPeriodMonths() {
        return periodMonths;
    }

    public void setPeriodMonths(int periodMonths) {
        this.periodMonths = periodMonths;
    }

    public Long getSumTake() {
        return sumTake;
    }

    public void setSumTake(Long sumTake) {
        this.sumTake = sumTake;
    }
}
