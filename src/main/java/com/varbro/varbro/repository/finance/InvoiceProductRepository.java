package com.varbro.varbro.repository.finance;


import com.varbro.varbro.model.finance.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}