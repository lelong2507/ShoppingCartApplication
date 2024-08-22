package com.example.ShoppingCartApplication.service;

import java.util.List;

import com.example.ShoppingCartApplication.model.OrderDetail;

public interface OrderDetailService {
    void addOrderDetails(OrderDetail orderDetail);

    List<OrderDetail> showListOd();
}
