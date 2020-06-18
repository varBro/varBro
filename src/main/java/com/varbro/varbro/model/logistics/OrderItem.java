package com.varbro.varbro.model.logistics;


import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double quantity;

    OrderItem() {}

    OrderItem(Product product, double quantity)
    {
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {return id;}

    public void setProduct(Product product) { this.product = product; }

    public Product getProduct() {return this.product; }

    public void setQuantity(double quantity) {this.quantity = quantity;}

    public double getQuantity() {return this.quantity;}
}
