package com.svartberg.springbootrest.repository;

import com.svartberg.springbootrest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
