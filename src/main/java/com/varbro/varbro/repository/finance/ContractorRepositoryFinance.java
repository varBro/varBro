package com.varbro.varbro.repository.finance;

import com.varbro.varbro.model.finance.ContractorFinance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepositoryFinance extends JpaRepository<ContractorFinance, Long> {
    //Contractor findByInvoiceId(long contractorId);
}