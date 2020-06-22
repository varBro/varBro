package com.varbro.varbro.service.logistics;


import com.varbro.varbro.model.finance.Expense;
import com.varbro.varbro.model.logistics.Order;
import com.varbro.varbro.repository.logistics.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order) {orderRepository.save(order);}

    public void saveOrders(List<Order> orders) {orderRepository.saveAll(orders);}

    public Iterable<Order> getOrders() {return orderRepository.findAll();}

    public void deleteAll() {orderRepository.deleteAll();}

    public Iterable<Order> getMonthlyOrders(String month, String year)
    {
        return orderRepository.monthlyOrders(month, year);
    }
}
