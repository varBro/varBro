package com.varbro.varbro.service.logistics;


import com.varbro.varbro.model.User;
import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.repository.logistics.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Optional<Order> getOrderById(Long id) {

        return orderRepository.findById(id);
    }

    public void saveOrder(Order order) {orderRepository.save(order);}

    public void saveOrders(List<Order> orders) {orderRepository.saveAll(orders);}

    public Iterable<Order> getOrders() {return orderRepository.findAll();}

    public void deleteAll() {orderRepository.deleteAll();}

    public Iterable<Order> getMonthlyOrders(String month, String year)
    {
        return orderRepository.monthlyOrders(month, year);
    }

    public Iterable<Order> getMonthlyOrdersApproved(String month, String year)
    {
        return orderRepository.monthlyOrdersApproved(month, year);
    }

    public Iterable<Order> getOrdersForApproval()
    {
        return orderRepository.ordersForApproval();
    }

    public Iterable<Order> getInProgressOrders()
    {
        return orderRepository.inProgressOrders();
    }
}
