package com.example.ShoppingCartApplication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ShoppingCartApplication.model.CartSession;
import com.example.ShoppingCartApplication.model.Product;
import com.example.ShoppingCartApplication.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ProductService productService;

    @GetMapping("/addToCart/{id}")
    public String handleViewCart(HttpSession session, Model model, @PathVariable("id") int id) {
        HashMap<Integer, CartSession> cartItems = (HashMap<Integer, CartSession>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }
        Product product = productService.findById(id);
        if (product != null) {
            if (cartItems.containsKey(id)) {
                CartSession item = cartItems.get(id);
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + 1);
                cartItems.put(id, item);
            } else {
                CartSession newItem = new CartSession();
                newItem.setProduct(product);
                newItem.setQuantity(1);
                cartItems.put(id, newItem);
            }
        }
        System.out.println(cartItems.size());
        session.setAttribute("cartItems", cartItems);
        session.setAttribute("totalCart", totalPrice(cartItems));
        session.setAttribute("cartNum", cartItems.size());

        return "redirect:/products/";
    }

    @GetMapping("/viewCart")
    public String viewCart(HttpSession session, Model model) {
        HashMap<Integer, CartSession> cartItems = (HashMap<Integer, CartSession>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new HashMap<>();
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalCart", totalPrice(cartItems));
        return "components/cart-check";
    }

    @PostMapping("/delete-item")
    public String handleDeleteItem(@RequestParam("id") int id, HttpSession session) {
        HashMap<Integer, CartSession> cartItems = (HashMap<Integer, CartSession>) session.getAttribute("cartItems");
        if (cartItems != null) {
            cartItems.remove(id);
            cartItems = new HashMap<>();
            session.setAttribute("cartItems", cartItems);
        }
        return "redirect:/carts/viewCart";
    }

    @GetMapping("/removeItem/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    public double totalPrice(HashMap<Integer, CartSession> cartItems) {
        double count = 0;
        for (Map.Entry<Integer, CartSession> list : cartItems.entrySet()) {
            count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
        }

        return count;
    }
}
