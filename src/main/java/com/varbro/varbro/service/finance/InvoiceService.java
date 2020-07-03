package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.User;
import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.repository.finance.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Iterable<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public double getSumOfMonthlyInvoices(String month, String year)
    {
        Iterable<Invoice> invoices = invoiceRepository.monthlyInvoices(month, year);
        double sum = 0;
        for (Invoice invoice:invoices) {
            sum += invoice.getTotalCost();
        }
        return sum;
    }

    public Iterable<Invoice> getMonthlyInvoices(String month, String year)
    {
        return invoiceRepository.monthlyInvoices(month, year);
    }
}
