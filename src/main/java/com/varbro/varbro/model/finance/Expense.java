package com.varbro.varbro.model.finance;


import com.varbro.varbro.model.logistics.Contractor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private long id;
    @Size(min = 2, max = 30)
    @NotBlank
    private String title;
    @NotBlank
    private String receiverName;
    private LocalDate date;
    @NotNull(message = "must not be empty")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;


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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}
