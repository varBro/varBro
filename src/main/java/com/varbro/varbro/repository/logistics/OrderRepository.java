package com.varbro.varbro.repository.logistics;


import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders o where extract(month from o.order_time) = ?1 and extract(year from o.order_time) = ?2",
            nativeQuery=true)
    List<Order> monthlyOrders(String month, String year);
}
