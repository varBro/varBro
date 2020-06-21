package com.varbro.varbro.model.production;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.logistics.Product;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beer_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "beer")
    Set<BeerIngredient> ingredients;

    public Beer() {}

    public Beer(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BeerIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<BeerIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(BeerIngredient ingredient) {
        this.ingredients.add(ingredient);
    }


}
