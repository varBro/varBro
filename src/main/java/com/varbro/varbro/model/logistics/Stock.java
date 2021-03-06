package com.varbro.varbro.model.logistics;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private long id;
    @NotNull
    private double quantity;
    @NotNull
    private LocalDate lastUpdated;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Stock() {}

    public Stock(Product product)
    {
        this.product = product;
        this.quantity = 0;
        this.lastUpdated = LocalDate.now();
    }

    public Stock(Product product, double quantity)
    {
        this.product = product;
        this.quantity = quantity;
        this.lastUpdated = LocalDate.now();
    }


    public Product getProduct() { return this.product; }

    public void setProduct(Product product) { this.product = product; }

    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getQuantity() { return this.quantity; }

    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDate getLastUpdated() { return this.lastUpdated; }

    public void stockUpdatedDate() { this.lastUpdated = LocalDate.now(); }

    public void add(double quantity) { this.quantity += quantity; }

    public void substitute(double quantity) { this.quantity -= quantity; }

}
