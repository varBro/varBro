package com.varbro.varbro.model.logistics;


import com.varbro.varbro.model.User;
import com.varbro.varbro.model.production.Request;

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
    @Column(name = "order_id")
    private long id;
    private LocalDate orderTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;

    public enum Status {
        PLACED("Placed"),
        IN_PROGRESS("In progress"),
        RECEIVED("Received"),
        APPROVED("Approved");

        private String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }
    }

    public Order() {
        this.orderItems = new ArrayList<OrderItem>();
        this.orderItems.add(new OrderItem());
    }

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderTime = LocalDate.now();
        this.orderStatus = Status.PLACED;
        this.contractor = null;
        this.request = null;
    }

    public long getId() {
        return this.id;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDate getOrderTime() {
        return this.orderTime;
    }

    public void setOrderStatus(Status status) {
        this.orderStatus = status;
    }

    public Status getOrderStatus() {
        return this.orderStatus;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public void setRequest(Request request) {this.request = request;}

    public Request getRequest() { return this.request; }

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
}
