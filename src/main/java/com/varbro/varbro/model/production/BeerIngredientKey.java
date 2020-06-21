package com.varbro.varbro.model.production;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BeerIngredientKey implements Serializable {
    @Column(name = "beer_id")
    Long beerId;

    @Column(name = "ingredient_id")
    Long ingredientId;

    public Long getBeerId() {
        return beerId;
    }

    public void setBeerId(Long beerId) {
        this.beerId = beerId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
