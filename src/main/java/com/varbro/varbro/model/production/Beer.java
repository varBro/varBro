package com.varbro.varbro.model.production;

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

    @ManyToMany
    @JoinTable(name = "beer_ingredient",
                joinColumns = @JoinColumn(name = "beer_id"),
                inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "product_id"))
    Set<Product> ingredients;

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

    public Set<Product> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Product> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Product ingredient) {
        this.ingredients.add(ingredient);
    }


}
