package com.varbro.varbro.model.finance;

import javax.persistence.*;

@Entity
@Table(name = "contractor_finance")
public class ContractorFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contractor_finance_id")
    private Long id;

    public ContractorFinance() {
    }

    public ContractorFinance(String name, String address, String identificationNumber) {
        this.name = name;
        this.address = address;
        this.identificationNumber = identificationNumber;
    }

    private String name;
    private String address;
    private String identificationNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
