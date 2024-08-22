package com.example.ShoppingCartApplication.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/viewDetail")
public class OrderDetailController {
    @GetMapping("order/{id}")
    public String handleViewDetail(@PathVariable("id") int id, Model model){
        
        return "";
    }
}
