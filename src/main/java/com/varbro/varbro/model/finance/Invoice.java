package com.varbro.varbro.model.finance;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_id")
    private Long id;
    private LocalDate date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="contractor_finance_id")
    ContractorFinance contractorFinance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="invoice_id")
    private List<InvoiceProduct> products;

    public Invoice() {
        products = new ArrayList<InvoiceProduct>();
        products.add(new InvoiceProduct());
    }

    public Invoice(LocalDate date, ContractorFinance contractorFinance, List<InvoiceProduct> products) {
        this.date = date;
        this.contractorFinance = contractorFinance;
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

    public ContractorFinance getContractorFinance() {
        return contractorFinance;
    }

    public void setContractorFinance(ContractorFinance contractorFinance) {
        this.contractorFinance = contractorFinance;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for(InvoiceProduct product : this.products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }
}