package com.varbro.varbro.model.Logistics;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@SecondaryTable(name = "stock", pkJoinColumns = @PrimaryKeyJoinColumn(name = "commodity_id"))
public class Commodity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "commodity_id")
        private long id;
        @NotBlank
        private String type;

        @Column(table = "stock")
        private float quantity;
        @Column(table = "stock")
        private LocalDate lastUpdated;

        public Commodity() {};

        public Commodity(String type)
        {
                this.type = type;
        }

        public Commodity(String type, float quantity)
        {
                this.type = type;
                this.quantity = quantity;
                this.lastUpdated = LocalDate.now();
        }

        void setId(long id) { this.id = id; }

        long getId() { return this.id; }

        void setType(String type) { this.type = type; }

        String getType() { return this.type; }

        void setQuantity(float quantity) { this.quantity = quantity; }

        float getQuantity() { return this.quantity; }

        void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

        LocalDate getLastUpdated() { return this.lastUpdated; }
}
