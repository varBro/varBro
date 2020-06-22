package com.varbro.varbro.model.production;

import com.varbro.varbro.model.production.BeerIngredient;
import com.varbro.varbro.model.logistics.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beer_id")
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beer", fetch = FetchType.EAGER, orphanRemoval = true)
    Set<BeerIngredient> beerIngredients;

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

    /*public Set<BeerIngredient> getBeerIngredients() {
        return beerIngredients;
    }

    public void setBeerIngredients(Set<BeerIngredient> beerIngredients) {
        this.beerIngredients = beerIngredients;
    }*/

    public void addIngredient(Product ingredient) {
        BeerIngredient beerIngredient = new BeerIngredient(this, ingredient);
        beerIngredients.add(beerIngredient);
    }

    public void removeIngredient(Product ingredient) {
        final Iterator<BeerIngredient> iterator = beerIngredients.iterator();
        while (iterator.hasNext()) {
            final BeerIngredient beerIngredient = iterator.next();
            if (beerIngredient.getProduct().getId() == ingredient.getId()) {
                iterator.remove();
            }
        }
    }

    public void setIngredientQuantity(Product ingredient, float quantity) {
        for (BeerIngredient bIn : beerIngredients) {
            if (bIn.getProduct().getId() == ingredient.getId()) {
                bIn.setQuantity(quantity);
                return;
            }
        }
    }

    public Set<Product> getIngredients() {
        final HashSet<Product> ingredients = new HashSet<>();
        for ( BeerIngredient bIn : beerIngredients) {
            ingredients.add(bIn.getProduct());
        }
        return ingredients;
    }

}
