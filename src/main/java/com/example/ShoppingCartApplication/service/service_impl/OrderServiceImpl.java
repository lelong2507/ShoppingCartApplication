package com.example.ShoppingCartApplication.service.service_impl;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingCartApplication.model.Order;
import com.example.ShoppingCartApplication.model.OrderDetail;
import com.example.ShoppingCartApplication.repository.OrderRepository;
import com.example.ShoppingCartApplication.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(Order order, List<OrderDetail> orderDetails) {
        for (OrderDetail od : orderDetails) {
            od.setOrder(order);
        }

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
    }

    @Override
    public List<Order> showList() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order getOrder = order.get();
            return getOrder;
        }

        return null;
    }

}
