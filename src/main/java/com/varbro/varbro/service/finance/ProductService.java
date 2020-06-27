package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.finance.Contractor;
import com.varbro.varbro.model.finance.Product;
import com.varbro.varbro.repository.finance.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProducts(Set<Product> products) {
        productRepository.saveAll(products);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public Product getOne(long id) {
        return productRepository.getOne(id);
    }

    public Iterable<Product> getProducts() {

        return productRepository.findAll();
    }
}
