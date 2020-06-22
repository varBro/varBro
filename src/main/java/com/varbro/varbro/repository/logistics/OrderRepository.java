package com.varbro.varbro.repository.logistics;


import com.varbro.varbro.model.User;
import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);

    @Query(value = "select * from orders o where extract(month from o.order_time) = ?1 and extract(year from o.order_time) = ?2",
            nativeQuery=true)
    List<Order> monthlyOrders(String month, String year);

    @Query(value = "select * from orders o where extract(month from o.order_time) = ?1 " +
            "and extract(year from o.order_time) = ?2 and o.order_status != 'PLACED'",
            nativeQuery=true)
    List<Order> monthlyOrdersApproved(String month, String year);

    @Query(value = "select * from orders o where o.order_status = 'IN_PROGRESS'",
            nativeQuery=true)
    List<Order> inProgressOrders();

    @Query(value = "select * from orders o where o.order_status = 'PLACED'",
            nativeQuery=true)
    List<Order> ordersForApproval();
}
