package com.example.ShoppingCartApplication.service.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ShoppingCartApplication.model.OrderDetail;
import com.example.ShoppingCartApplication.repository.OrderDetailRepository;
import com.example.ShoppingCartApplication.service.OrderDetailService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void addOrderDetails(OrderDetail orderDetail) {

    }

    @Override
    public List<OrderDetail> showListOd() {
        return orderDetailRepository.findAll();
    }

}
