package com.varbro.varbro.repository.production;

import com.varbro.varbro.model.production.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

    Beer findByName(String name);

    List<Beer> findAllByOrderByName();

    List<Beer> findByNameContainingIgnoreCase(String name);
}
