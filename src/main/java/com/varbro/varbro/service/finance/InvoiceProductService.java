package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.finance.InvoiceProduct;
import com.varbro.varbro.model.finance.Product;
import com.varbro.varbro.repository.finance.InvoiceProductRepository;
import com.varbro.varbro.repository.finance.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceProductService {
    @Autowired
    InvoiceProductRepository invoiceProductRepository;

    public void saveInvoiceProduct(InvoiceProduct invoiceProduct) {
        invoiceProductRepository.save(invoiceProduct);
    }

    public void saveInvoiceProduct(Set<InvoiceProduct> invoiceProducts) {
        invoiceProductRepository.saveAll(invoiceProducts);
    }

    public void delete(InvoiceProduct invoiceProduct) {
        invoiceProductRepository.delete(invoiceProduct);
    }

    public void deleteAll() {
        invoiceProductRepository.deleteAll();
    }

    public InvoiceProduct getOne(long id) {
        return invoiceProductRepository.getOne(id);
    }

    public Iterable<InvoiceProduct> getContractors() {

        return invoiceProductRepository.findAll();
    }
}
