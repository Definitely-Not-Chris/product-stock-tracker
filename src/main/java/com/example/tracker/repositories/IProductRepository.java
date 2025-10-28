package com.example.tracker.repositories;

import com.example.tracker.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Product findAllBySku(String sku);
}
