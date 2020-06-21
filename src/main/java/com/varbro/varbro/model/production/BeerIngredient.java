package com.varbro.varbro.model.production;

import com.varbro.varbro.model.logistics.Product;

import javax.persistence.*;

@Entity
@Table(name = "beer_ingredient")
public class BeerIngredient {

    @EmbeddedId
    BeerIngredientKey id;

    @ManyToOne
    @MapsId("beer_id")
    @JoinColumn(name = "beer_id")
    Beer beer;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "ingredient_id")
    Product product;

    float quantity;
}
