package com.varbro.varbro.model.logistics;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contractor_id")
    private long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    @Pattern(regexp = "[\\d]{10}")
    private String nip;
    @Pattern(regexp = "[\\d]{9}")
    private String regon;
    @NotBlank
    private String address;

    public Contractor() {
    }

    public Contractor(String name, String nip, String regon, String address) {
        this.name = name;
        this.nip = nip;
        this.regon = regon;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
