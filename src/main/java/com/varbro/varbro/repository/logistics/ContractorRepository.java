package com.varbro.varbro.repository.logistics;

import com.varbro.varbro.model.logistics.Contractor;
import com.varbro.varbro.model.logistics.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Contractor findByName(String name);
}
