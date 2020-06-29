package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.repository.finance.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public void saveInvoices(Iterable<Invoice> invoices) {
        invoiceRepository.saveAll(invoices);
    }

    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public void deleteAll() {
        invoiceRepository.deleteAll();
    }

    public Invoice getOne(long id) {
        return invoiceRepository.getOne(id);
    }
}
