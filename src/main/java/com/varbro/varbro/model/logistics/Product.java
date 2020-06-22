package com.varbro.varbro.model.logistics;

import com.varbro.varbro.model.production.BeerIngredient;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

        private boolean ingredient;

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
