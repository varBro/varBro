package com.varbro.varbro.repository.production;

import java.util.List;
import com.varbro.varbro.model.production.Vat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VatRepository extends JpaRepository<Vat, Long> {

    List<Vat> findAllByOrderByCapacityDesc();
}
