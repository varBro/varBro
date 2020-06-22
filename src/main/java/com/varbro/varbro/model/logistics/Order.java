package com.varbro.varbro.model.logistics;


import com.varbro.varbro.model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    public enum Status {
        PLACED,
        IN_PROGRESS,
        RECEIVED,
        APPROVED
    }

    public Order() {
        this.orderItems = new ArrayList<OrderItem>();
        this.orderItems.add(new OrderItem());
    }

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderTime = LocalDate.now();
        orderStatus = Status.IN_PROGRESS;
    }

    public long getId() {return this.id;}

    public List<OrderItem> getOrderItems() {return this.orderItems;}

    public void setOrderItems(List<OrderItem> orderItems) {this.orderItems = orderItems;}

    public void setOrderTime(LocalDate orderTime) { this.orderTime = orderTime; }

    public LocalDate getOrderTime() { return this.orderTime; }

    public void setOrderStatus(Status status) { this.orderStatus = status; }

    public Status getOrderStatus() { return this.orderStatus; }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
  
      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
  
}
