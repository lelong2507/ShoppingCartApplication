package com.example.ShoppingCartApplication.service;

import java.util.List;

import com.example.ShoppingCartApplication.model.Order;
import com.example.ShoppingCartApplication.model.OrderDetail;

public interface OrderService {
    void addOrder(Order order, List<OrderDetail> orderDetail);

    List<Order> showList();

    Order findById(int id);
}
