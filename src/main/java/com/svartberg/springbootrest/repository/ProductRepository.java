package com.svartberg.springbootrest.repository;

import com.svartberg.springbootrest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductsByRequestsId(Long requestId);
}
