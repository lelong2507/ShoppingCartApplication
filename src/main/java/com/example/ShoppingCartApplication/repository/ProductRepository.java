package com.example.ShoppingCartApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ShoppingCartApplication.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    
}
