package com.varbro.varbro.model.logistics;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private long id;
    private double quantity;
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


    public Product getProduct() {return this.product; }

    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getQuantity() { return this.quantity; }

    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDate getLastUpdated() { return this.lastUpdated; }

}
