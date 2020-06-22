package com.varbro.varbro.model.logistics;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private long id;
    @NotBlank
    private double quantity;
    @NotBlank
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

    @AssertTrue(message = "Product defined in pcs. must be an integer!")
    public boolean isUnitOK() {
        return (product.getUnit() != Product.Unit.PCS || quantity == Math.floor(quantity));
    }

}
