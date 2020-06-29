package com.varbro.varbro.model.finance;

import javax.persistence.*;

@Entity
@Table(name = "product_finance")
public class ProductFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_finance_id")
    private Long id;
    private String name;
    private double price;

    public ProductFinance() {
    }

    public ProductFinance(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
