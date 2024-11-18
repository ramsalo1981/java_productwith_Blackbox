package com.ramis.ramiproduct.Controller;

import com.ramis.ramiproduct.Repository.ProductRepository;
import com.ramis.ramiproduct.entity.Product;
import com.ramis.ramiproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.update(id, productDetails);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Search by name
    @GetMapping("/search/name")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    // Search by description
    @GetMapping("/search/description")
    public List<Product> searchByDescription(@RequestParam String description) {
        return productService.searchByDescription(description);
    }

    // Search by price range
    @GetMapping("/search/price")
    public List<Product> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.searchByPriceRange(minPrice, maxPrice);
    }
}
