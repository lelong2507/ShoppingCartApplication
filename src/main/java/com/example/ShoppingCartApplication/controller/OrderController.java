package com.example.ShoppingCartApplication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ShoppingCartApplication.model.CartSession;
import com.example.ShoppingCartApplication.model.Order;
import com.example.ShoppingCartApplication.model.OrderDetail;
import com.example.ShoppingCartApplication.service.OrderService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkOut")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderList")
    public String handleShowListOrder(Model model) {
        List<Order> orders = orderService.showList();
        model.addAttribute("orders", orders);
        return "components/order-list";
    }

    @GetMapping("/show-infor-bill")
    public String handleShowBill(HttpSession session, Model model) {
        HashMap<Integer, CartSession> cartItems = (HashMap<Integer, CartSession>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        if (!cartItems.isEmpty()) {
            Iterator<Map.Entry<Integer, CartSession>> it = cartItems.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, CartSession> entry = it.next();
                Integer key = entry.getKey();
                CartSession value = entry.getValue();
                System.out.println(cartItems.size());
                System.out.println("Product ID: " + key);
                System.out.println("Product Name: " + value.getProduct().getName());
                System.out.println("Quantity: " + value.getQuantity());
            }
        }

        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice(cartItems));

        return "components/showBill";
    }

 

    @PostMapping("/create-order")
    public String handleOrder(@ModelAttribute("order") Order order, HttpSession session) {
        HashMap<Integer, CartSession> cartItems = (HashMap<Integer, CartSession>) session.getAttribute("cartItems");
        List<OrderDetail> orderDetails = new ArrayList<>();
        order.setOrderDate(LocalDate.now());
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        if (!cartItems.isEmpty()) {
            for (Map.Entry<Integer, CartSession> entry : cartItems.entrySet()) {
                CartSession value = entry.getValue();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(value.getProduct());
                orderDetail.setQuantity(value.getQuantity());
                orderDetails.add(orderDetail);
            }
        }
        orderService.addOrder(order, orderDetails);
        return "redirect:/checkOut/orderList";
    }

    public double totalPrice(HashMap<Integer, CartSession> cartItems) {
        double count = 0;
        for (Map.Entry<Integer, CartSession> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }

        return count;
    }
}
