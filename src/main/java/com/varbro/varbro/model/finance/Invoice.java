package com.varbro.varbro.model.finance;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    private Long id;
}