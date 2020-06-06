package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    //Invoice findByInvoiceId(long invoiceId);
}