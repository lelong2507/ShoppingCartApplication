package com.example.ShoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCartApplication.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
