package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void create(final ProductDTO productDTO);

    ProductDTO read(Long id);

    ProductDTO readByRequestId(Long requestId, Long productId);

    List<ProductDTO> readAll();

    List<ProductDTO> readAllByRequestId(Long id);

    boolean update(Long id, ProductDTO productDTO);

    boolean delete(Long id);
}
