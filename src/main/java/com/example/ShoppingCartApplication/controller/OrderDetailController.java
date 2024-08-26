package com.example.ShoppingCartApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ShoppingCartApplication.model.Order;
import com.example.ShoppingCartApplication.model.OrderDetail;
import com.example.ShoppingCartApplication.service.OrderService;

@RequestMapping("/viewDetail")
public class OrderDetailController {

    @Autowired
    private OrderService orderService;

    public OrderDetailController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/{id}")
    public String handleViewDetail(@PathVariable("id") int id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) {
            return "redirect:/checkOut/orderList";
        }

        for (OrderDetail od : order.getOrderDetails()) {
            System.out.println("Order ID: " + od.getOrder().getId());
            System.out.println("Name: " + od.getProduct().getName());
            System.out.println("Price: " + od.getProduct().getPrice());
            System.out.println("Date: " + od.getOrder().getOrderDate());
        }

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "components/order-check";
    }
}
