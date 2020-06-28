package com.varbro.varbro.repository.production;

import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(Request.Status status);
    List<Request> findByStatusOrderByTime(Request.Status status);
}
