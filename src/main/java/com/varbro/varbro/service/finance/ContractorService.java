package com.varbro.varbro.service.finance;

import com.varbro.varbro.model.Role;
import com.varbro.varbro.model.finance.Contractor;
import com.varbro.varbro.model.finance.Invoice;
import com.varbro.varbro.repository.finance.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ContractorService {
    @Autowired
    ContractorRepository contractorRepository;

    public void saveContractor(Contractor contractor) {
        contractorRepository.save(contractor);
    }

    public void saveContractors(Iterable<Contractor> contractors) {
        contractorRepository.saveAll(contractors);
    }

    public void delete(Contractor contractor) {
        contractorRepository.delete(contractor);
    }

    public void deleteAll() {
        contractorRepository.deleteAll();
    }

    public Contractor getOne(long id) {
        return contractorRepository.getOne(id);
    }

    public Iterable<Contractor> getContractors() {

        return contractorRepository.findAll();
    }
}
