package com.varbro.varbro.service.logistics;

import com.varbro.varbro.model.logistics.Contractor;
import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.repository.logistics.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {

    @Autowired
    ContractorRepository contractorRepository;

    public void saveContractor(Contractor contractor) { contractorRepository.save(contractor); }

    public Iterable<Contractor> getContractors() {
        return contractorRepository.findAll();
    }

    public Contractor getContractorByName(String name) {

        return contractorRepository.findByName(name);
    }
}
