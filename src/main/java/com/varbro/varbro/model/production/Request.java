package com.varbro.varbro.model.production;

import com.varbro.varbro.model.logistics.Order;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private long id;
    @Enumerated(EnumType.STRING)
    private Request.Status status;
    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;
    @NotNull
    @Min(value = 1)
    private int amount;

    @NotNull
    private boolean enoughIngredients;
    @NotNull
    private boolean ready;

    public enum Status {
        PENDING,
        RESOLVED
    }

    public Request() {}

    public Request(Beer beer, int amount) {
        this.beer = beer;
        this.amount = amount;
        this.status = Status.PENDING;
        this.enoughIngredients = false;
        this.ready = false;
    }

    public Request(Beer beer, int amount, boolean enoughIngredients) {
        this.beer = beer;
        this.amount = amount;
        this.status = Status.PENDING;
        this.enoughIngredients = enoughIngredients;
        this.ready = false;
    }

    public long getId() {return this.id;}

    public void setStatus(Request.Status status) {this.status = status;}

    public Request.Status getStatus() {return this.status;}

    public void setBeer(Beer beer) { this.beer = beer;}

    public Beer getBeer() {return this.beer;}

    public void setAmount(int amount) {this.amount = amount;}

    public int getAmount() {return this.amount;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(getId(), request.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
