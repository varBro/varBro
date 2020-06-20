package com.varbro.varbro.model.logistics;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id")
    private long id;
    private LocalDate orderTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_item_id")
    private List<OrderItem> orderItems;

    public Order() {
        this.orderItems = new ArrayList<OrderItem>();
        this.orderItems.add(new OrderItem());
    }

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderTime = LocalDate.now();
    }

    public long getId() {return this.id;}

    public List<OrderItem> getOrderItems() {return this.orderItems;}

    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}

    public void setOrderTime(LocalDate orderTime) { this.orderTime = orderTime; }

    public LocalDate getOrderTimed() { return this.orderTime; }
}
