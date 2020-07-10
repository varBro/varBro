package com.varbro.varbro.model.logistics;

import com.varbro.varbro.model.production.BeerIngredient;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "product_id")
        private long id;
        @Column(unique = true)
        @NotEmpty
        private String name;
        @Enumerated(EnumType.STRING)
        private Unit unit;

        @NotNull
        private boolean ingredient;

        @Enumerated(EnumType.STRING)
        private IngredientType ingredientType;

        @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
        private List<BeerIngredient> beerIngredients = new ArrayList<>();

        public enum Unit {
                PCS {
                        @Override
                        public String toString() {
                                return "pcs.";
                        }
                },
                KG {
                        @Override
                        public String toString() {
                                return "kilograms";
                        }
                }
        }

        public enum IngredientType {
                HOP,
                YEAST,
                MALT
        }
        
        public Product() {}

        public Product(String name, Unit unit)
        {
                this.unit = unit;
                this.name = name;
                this.ingredient = false;
        }

        public Product(String name, Unit unit, boolean ingredient)
        {
                this.unit = unit;
                this.name = name;
                this.ingredient = ingredient;
        }

        public Product(String name, Unit unit, IngredientType type)
        {
                this.unit = unit;
                this.name = name;
                this.ingredient = true;
                this.ingredientType = type;
        }

        public long getId() { return this.id; }

        public void setName(String type) { this.name = type; }

        public String getName() { return this.name; }

        public Unit getUnit() { return this.unit; }

        public void setUnit(Unit unit) { this.unit = unit; }

        public List<BeerIngredient> getBeerIngredients() {
                return beerIngredients;
        }

        public void setBeerIngredients(List<BeerIngredient> beerIngredients) {
                this.beerIngredients = beerIngredients;
        }

        public boolean isIngredient() { return ingredient; }

        public void setIngredient(boolean ingredient) { this.ingredient = ingredient; }

        public IngredientType getIngredientType() {
                return ingredientType;
        }

        public void setIngredientType(IngredientType ingredientType) {this.ingredientType = ingredientType;}

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Product)) return false;
                Product product = (Product) o;
                return Objects.equals(getId(), product.getId());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getId());
        }
}
