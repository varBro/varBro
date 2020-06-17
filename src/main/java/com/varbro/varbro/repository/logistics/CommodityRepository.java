package com.varbro.varbro.repository.logistics;

import com.varbro.varbro.model.Logistics.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {
}
