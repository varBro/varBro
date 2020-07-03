package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.ProductFinance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryFinance extends JpaRepository<ProductFinance, Long> {
    //Product findByInvoiceId(long producteId);
}