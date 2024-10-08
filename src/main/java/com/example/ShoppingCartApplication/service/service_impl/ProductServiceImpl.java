package com.example.ShoppingCartApplication.service.service_impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ShoppingCartApplication.model.Product;
import com.example.ShoppingCartApplication.repository.ProductRepository;
import com.example.ShoppingCartApplication.service.ProductService;
import com.example.ShoppingCartApplication.utils.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private FileUtils fileUtils;
    @Override
    public void addProduct(Product product){
        productRepository.save(product);
    }

    @Override
    public List<Product> showAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isPresent()) {
            Product getProduct = product.get();
            return getProduct;
        }
        return null;
    }

}
