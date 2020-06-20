package com.varbro.varbro.model.logistics;

import com.varbro.varbro.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "product_id")
        private long id;
        @Column(unique = true)
        @NotBlank
        private String name;

        public Product() {}

        public Product(String name)
        {
                this.name = name;
        }

        public long getId() { return this.id; }

        public void setName(String type) { this.name = type; }

        public String getName() { return this.name; }

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
