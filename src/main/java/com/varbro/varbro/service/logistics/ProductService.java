package com.varbro.varbro.service.logistics;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.repository.logistics.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void saveProducts(List<Product> products) { productRepository.saveAll(products); }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void deleteAll() { productRepository.deleteAll(); }

    public Product getProductByName(String name) {

        return productRepository.findByName(name);
    }
}
