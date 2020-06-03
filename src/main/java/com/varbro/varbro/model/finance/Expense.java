package com.varbro.varbro.model.finance;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private long id;
    private String title;
    private String receiverName;
    private LocalDate date;
    private BigDecimal amount;

    public Expense() {
    }

    public Expense(String title, String receiverName, BigDecimal amount) {
        this.title = title;
        this.receiverName = receiverName;
        this.amount = amount;
        this.date = LocalDate.now();
    }


}
