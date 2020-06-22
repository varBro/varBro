package com.varbro.varbro.service.logistics;

import com.varbro.varbro.model.logistics.Contractor;
import com.varbro.varbro.repository.logistics.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    ContractorRepository contractorRepository;

    public void saveContractor(Contractor contractor) {
        contractorRepository.save(contractor);
    }

    public void saveContractors(List<Contractor> contractors) {
        contractorRepository.saveAll(contractors);
    }

    public void deleteAll() {
        contractorRepository.deleteAll();
    }

    public Iterable<Contractor> getContractors() {
        return contractorRepository.findAll();
    }
}
