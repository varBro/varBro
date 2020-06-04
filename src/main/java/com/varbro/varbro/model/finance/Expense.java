package com.varbro.varbro.model.finance;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String receiverName;
    private LocalDate date;
    @NotNull
    private BigDecimal amount;

    public Expense() {
        this.date = LocalDate.now();
    }

    public Expense(String title, String receiverName, BigDecimal amount) {
        this.title = title;
        this.receiverName = receiverName;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
