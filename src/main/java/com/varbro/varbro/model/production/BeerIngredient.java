package com.varbro.varbro.model.production;

import com.varbro.varbro.model.logistics.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "beer_ingredient")
public class BeerIngredient implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @Id
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Product product;

    private float quantity;

    public BeerIngredient() {}

    public BeerIngredient(Beer beer, Product product) {
        this.beer = beer;
        this.product = product;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
