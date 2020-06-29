package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.finance.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "select * from invoice i where extract(month from i.date) = ?1 and extract(year from i.date) = ?2",
            nativeQuery=true)
    List<Invoice> monthlyInvoices(String month, String year);
}