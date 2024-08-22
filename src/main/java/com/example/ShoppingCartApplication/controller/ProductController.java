package com.example.ShoppingCartApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ShoppingCartApplication.model.Product;
import com.example.ShoppingCartApplication.service.ProductService;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String handleViewProduct(Model model) {
        List<Product> products = productService.showAllProduct();
        System.out.println(products.size());
        model.addAttribute("productList", products);

        return "index";
    }

}
