package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.finance.ContractorFinance;
import com.varbro.varbro.repository.finance.ContractorRepositoryFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorServiceFinance {
    @Autowired
    ContractorRepositoryFinance contractorRepositoryFinance;

    public void saveContractor(ContractorFinance contractorFinance) {
        contractorRepositoryFinance.save(contractorFinance);
    }

    public void saveContractors(List<ContractorFinance> contractorFinances) {
        contractorRepositoryFinance.saveAll(contractorFinances);
    }

    public void delete(ContractorFinance contractorFinance) {
        contractorRepositoryFinance.delete(contractorFinance);
    }

    public void deleteAll() {
        contractorRepositoryFinance.deleteAll();
    }

    public ContractorFinance getOne(long id) {
        return contractorRepositoryFinance.getOne(id);
    }

    public Iterable<ContractorFinance> getContractors() {

        return contractorRepositoryFinance.findAll();
    }
}
