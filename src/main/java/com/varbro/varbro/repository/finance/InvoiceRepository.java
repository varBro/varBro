package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    //Invoice findByInvoiceId(long invoiceId);
}