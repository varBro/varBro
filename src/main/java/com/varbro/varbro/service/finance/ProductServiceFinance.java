package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.finance.ProductFinance;
import com.varbro.varbro.repository.finance.ProductRepositoryFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceFinance {
    @Autowired
    ProductRepositoryFinance productRepositoryFinance;

    public void saveProduct(ProductFinance productFinance) {
        productRepositoryFinance.save(productFinance);
    }

    public void saveProducts(Iterable<ProductFinance> products) {
        productRepositoryFinance.saveAll(products);
    }

    public void delete(ProductFinance productFinance) {
        productRepositoryFinance.delete(productFinance);
    }

    public void deleteAll() {
        productRepositoryFinance.deleteAll();
    }

    public ProductFinance getOne(long id) {
        return productRepositoryFinance.getOne(id);
    }

    public Iterable<ProductFinance> getProducts() {

        return productRepositoryFinance.findAll();
    }
}
