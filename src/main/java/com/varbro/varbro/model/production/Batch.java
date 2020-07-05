package com.varbro.varbro.model.production;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "batch_id")
    private long id;
    @NotBlank
    private String beerName;
    @NotNull
    private int beerAmountInLiters;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "vat_id")
    private Vat vat;

    public Batch() {
    }

    public Batch(Vat vat) {
        this.beerName = vat.getBeer().getName();
        this.beerAmountInLiters = vat.getCapacity();
        this.vat = vat;
        this.date = LocalDate.now();
    }

    public Batch(String beerName, Vat vat, LocalDate date) {
        this.beerName = beerName;
        this.beerAmountInLiters = vat.getCapacity();
        this.vat = vat;
        this.date = date;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public int getBeerAmountInLiters() {
        return beerAmountInLiters;
    }

    public void setBeerAmountInLiters(int beerAmountInLiters) {
        this.beerAmountInLiters = beerAmountInLiters;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }
}
