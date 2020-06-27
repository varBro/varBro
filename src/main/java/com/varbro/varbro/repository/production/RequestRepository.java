package com.varbro.varbro.repository.production;

import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
