package com.varbro.varbro.model.production;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "beer_id")
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String recipeDescription;

    @OneToMany(mappedBy = "beer", cascade = CascadeType.ALL)
    private List<BeerIngredient> beerIngredients;

    public Beer() {}

    public Beer(String name, String recipeDescription, BeerIngredient... beerIngredients) {
        this.name = name;
        this.recipeDescription = recipeDescription;
        for(BeerIngredient beerIngredient : beerIngredients) beerIngredient.setBeer(this);
        this.beerIngredients = Stream.of(beerIngredients).collect(Collectors.toList());
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

    public List<BeerIngredient> getBeerIngredients() {
        return beerIngredients;
    }

    public void setBeerIngredients(List<BeerIngredient> beerIngredients) { 
        this.beerIngredients = beerIngredients; 
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id &&
                name.equals(beer.name) &&
                beerIngredients.equals(beer.beerIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, beerIngredients);
    }
}
