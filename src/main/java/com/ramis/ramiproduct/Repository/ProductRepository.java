package com.ramis.ramiproduct.Repository;

import com.ramis.ramiproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByDescriptionContainingIgnoreCase(String description);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
