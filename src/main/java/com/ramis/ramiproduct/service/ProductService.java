package com.ramis.ramiproduct.service;

import com.ramis.ramiproduct.Repository.ProductRepository;
import com.ramis.ramiproduct.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product productDetails) {
        Product product = findById(id);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            return productRepository.save(product);
        }
        return null; // or throw an exception
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    // Search by name
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Search by description
    public List<Product> searchByDescription(String description) {
        return productRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Search by price range
    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
