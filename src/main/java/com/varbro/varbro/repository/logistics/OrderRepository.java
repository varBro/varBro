package com.varbro.varbro.repository.logistics;


import com.varbro.varbro.model.logistics.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
