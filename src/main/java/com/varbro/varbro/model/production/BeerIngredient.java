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
    @JoinColumn(name = "product_id")
    private Product product;

    private float quantity;

    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public enum IngredientType {
        HOP,
        YEAST,
        MALT
    }

    public BeerIngredient() {}

    public BeerIngredient(Product product, float quantity, IngredientType ingredientType) {
        this.product = product;
        this.quantity = quantity;
        this.ingredientType = ingredientType;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public Product getIngredient() { return product; }

    public void setIngredient(Product product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() { 
        return product; 
    }

    public void setProduct(Product product) { 
        this.product = product; 
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }
}
