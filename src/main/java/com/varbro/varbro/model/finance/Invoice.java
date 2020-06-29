package com.varbro.varbro.model.finance;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_id")
    private Long id;
    private LocalDate date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contractor_id")
    Contractor contractor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="invoice_id")
    private List<InvoiceProduct> products;

    public Invoice() {
        products = new ArrayList<InvoiceProduct>();
        products.add(new InvoiceProduct());
    }

    public Invoice(LocalDate date, Contractor contractor, List<InvoiceProduct> products) {
        this.date = date;
        this.contractor = contractor;
        this.products = products;
    }

    public List<InvoiceProduct> getProducts() {
        return products;
    }

    public void setProducts(List<InvoiceProduct> products) {
        this.products = products;
    }

    public void addProduct(InvoiceProduct product) {
        this.products.add(product);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }
}