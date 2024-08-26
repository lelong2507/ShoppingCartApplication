package com.example.ShoppingCartApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ShoppingCartApplication.model.Product;
import com.example.ShoppingCartApplication.service.ProductService;
import com.example.ShoppingCartApplication.utils.FileUtils;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private FileUtils fileUtils;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String handleViewProduct(Model model) {
        List<Product> products = productService.showAllProduct();
        for (Product product : products) {
            if (product.getImg() != null) {
                String base64Image = Base64Utils.encodeToString(product.getImg());
                product.setImageBase64(base64Image);
            }
        }
        model.addAttribute("productList", products);

        return "index";
    }

    @GetMapping("/add")
    public String handleShowAddProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "components/add-product";
    }

    @PostMapping("/add")
    public String handleAddProduct(@ModelAttribute("product") Product product,
            @RequestParam("file") MultipartFile file,
            Model model) {
        try {
            fileUtils.validateFile(file);
            product.setImg(file.getBytes());
            productService.addProduct(product);
        } catch (Exception e) {
            System.out.println(e);
            return "components/add-product";
        }
        return "redirect:/products/";
    }

}
