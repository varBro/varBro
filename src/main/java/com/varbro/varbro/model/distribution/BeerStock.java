package com.varbro.varbro.model.distribution;

import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.model.production.Beer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "beer_stock")
public class BeerStock{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="beer_stock_id")
    private long id;

    @NotNull
    private double quantity;
    @NotNull
    private LocalDate lastUpdated;

    @OneToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    public BeerStock() {}

    public BeerStock(Beer beer)
    {
        this.beer = beer;
        this.quantity = 0;
        this.lastUpdated = LocalDate.now();
    }

    public BeerStock(Beer beer, double quantity)
    {
        this.beer = beer;
        this.quantity = quantity;
        this.lastUpdated = LocalDate.now();
    }

    public long getId() {return this.id;}

    public Beer getBeer() {return this.beer; }

    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getQuantity() { return this.quantity; }

    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

    public LocalDate getLastUpdated() { return this.lastUpdated; }

    public void beerStockUpdatedDate() { this.lastUpdated = LocalDate.now(); }

    public  void add(double quantity) { this.quantity += quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeerStock)) return false;
        BeerStock beerStock = (BeerStock) o;
        return Objects.equals(getId(), beerStock.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}