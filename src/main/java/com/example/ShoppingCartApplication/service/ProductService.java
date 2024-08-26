package com.example.ShoppingCartApplication.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.ShoppingCartApplication.model.Product;

public interface ProductService {
    void addProduct(Product product);

    List<Product> showAllProduct();

    Product findById(int idProduct);
}
