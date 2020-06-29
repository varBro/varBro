package com.varbro.varbro.model.finance;

import javax.persistence.*;

@Entity
public class InvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_finance_id")
    ProductFinance productFinance;

    public InvoiceProduct()
    {
    }

    public InvoiceProduct(ProductFinance productFinance, double amount) {
        this.productFinance = productFinance;
        this.amount = amount;
    }

    double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductFinance getProductFinance() {
        return productFinance;
    }

    public void setProductFinance(ProductFinance productFinance) {
        this.productFinance = productFinance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return productFinance.getPrice() * amount;
    }
}
