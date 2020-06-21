package com.varbro.varbro.repository.production;

import com.varbro.varbro.model.production.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
