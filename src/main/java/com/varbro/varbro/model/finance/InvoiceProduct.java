package com.varbro.varbro.model.finance;

import javax.persistence.*;

@Entity
public class InvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public InvoiceProduct()
    {
    }

    public InvoiceProduct(Product product, double amount) {
        this.product = product;
        this.amount = amount;
    }

    double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return product.getPrice();
    }
}
