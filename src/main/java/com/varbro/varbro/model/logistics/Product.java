package com.varbro.varbro.model.logistics;

import com.varbro.varbro.model.production.BeerIngredient;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "product_id")
        private long id;
        @Column(unique = true)
        @NotBlank
        private String name;
        @Enumerated(EnumType.STRING)
        private Unit unit;
        @NotNull
        private boolean ingredient;

        @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
        private Set<BeerIngredient> beerIngredients = new HashSet<>();

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

        public long getId() { return this.id; }

        public void setName(String type) { this.name = type; }

        public String getName() { return this.name; }

        public Unit getUnit() { return this.unit; }

        public void setUnit(Unit unit) { this.unit = unit; }

        public Set<BeerIngredient> getBeerIngredients() {
                return beerIngredients;
        }

        public void setBeerIngredients(Set<BeerIngredient> beerIngredients) {
                this.beerIngredients = beerIngredients;
        }

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
