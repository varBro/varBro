package com.varbro.varbro.model.logistics;

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
        private double quantity;
        @Column(table = "stock")
        private LocalDate lastUpdated;

        public Commodity() {};

        public Commodity(String type)
        {
                this.type = type;
        }

        public Commodity(String type, double quantity)
        {
                this.type = type;
                this.quantity = quantity;
                this.lastUpdated = LocalDate.now();
        }

        public void setId(long id) { this.id = id; }

        public long getId() { return this.id; }

        public void setType(String type) { this.type = type; }

        public String getType() { return this.type; }

        public void setQuantity(double quantity) { this.quantity = quantity; }

        public double getQuantity() { return this.quantity; }

        public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }

        public LocalDate getLastUpdated() { return this.lastUpdated; }
}
