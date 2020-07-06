package com.varbro.varbro.repository.distribution;

import com.varbro.varbro.model.distribution.BeerStock;
import com.varbro.varbro.model.logistics.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerStockRepository extends JpaRepository<BeerStock, Long> {

    Optional<BeerStock> findByBeerId(long id);
    Optional<BeerStock> findByBeerName(String name);
}