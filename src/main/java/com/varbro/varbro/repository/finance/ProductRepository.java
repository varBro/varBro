package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product findByInvoiceId(long producteId);
}