package com.varbro.varbro.repository.logistics;

import com.varbro.varbro.model.logistics.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(long id);

    @Query(value = "select quantity from stock s where s.product_id = ?1",
            nativeQuery=true)
    Optional<Object> findQuantityById(long id);
}
