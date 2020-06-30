package com.varbro.varbro.model.production;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beer_id")
    private long id;
    private String name;
    private String recipeDescription;

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL)
    private Set<BeerIngredient> beerIngredients;

    public Beer() {}

    public Beer(String name, String recipeDescription, BeerIngredient... beerIngredients) {
        this.name = name;
        this.recipeDescription = recipeDescription;
        for(BeerIngredient beerIngredient : beerIngredients) beerIngredient.setBeer(this);
        this.beerIngredients = Stream.of(beerIngredients).collect(Collectors.toSet());
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

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public Set<BeerIngredient> getBeerIngredients() {
        return beerIngredients;
    }

    public void setBeerIngredients(Set<BeerIngredient> beerIngredients) {
        this.beerIngredients = beerIngredients;
    }

}
