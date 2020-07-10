package com.varbro.varbro.repository.logistics;

import com.varbro.varbro.model.logistics.Product;
import com.varbro.varbro.model.logistics.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(long id);

    @Query(value = "select quantity from stock s where s.product_id = ?1",
            nativeQuery=true)
    Optional<Object> findQuantityById(long id);

    @Query(value = "select quantity from stock s natural join product p where p.name = 'Bottle'",
            nativeQuery=true)
    Optional<Object> findBottlesQuantity();

    @Query(value = "select stock_id, last_updated, quantity, product_id from stock s natural join product p " +
            "where p.ingredient = 1 order by p.ingredient_type", nativeQuery=true)
    List<Stock> findIngredients();

    @Query(value = "select stock_id, last_updated, quantity, product_id from stock s natural join product p where p.ingredient = 0",
            nativeQuery=true)
    List<Stock> findNotIngredients();
}
