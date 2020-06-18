package com.varbro.varbro.model.logistics;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;
    private LocalDate orderTime;
    @OneToMany
    @JoinColumn(name = "order_item_id")
    private Set<OrderItem> orderItems;

    Order() {}

    Order(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderTime = LocalDate.now();
    }

    public long getId() {return this.id;}

    public Set<OrderItem> getOrderItems() {return this.orderItems;}

    public void setOrderItems(Set<OrderItem> orderItems) {this.orderItems = orderItems;}

    public void setOrderTime(LocalDate orderTime) { this.orderTime = orderTime; }

    public LocalDate getOrderTimed() { return this.orderTime; }
}
