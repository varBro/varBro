package com.varbro.varbro.model.finance;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_id")
    private Long id;
    private double price;
    private LocalDate date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contractor_id")
    Contractor contractor;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}