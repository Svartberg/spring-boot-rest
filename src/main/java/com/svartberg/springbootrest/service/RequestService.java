package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.dto.ProductRequestDTO;
import com.svartberg.springbootrest.dto.RequestDTO;

import java.util.List;

public interface RequestService {

    void create(RequestDTO requestDTO);

    ProductRequestDTO read(Long id);

    ProductRequestDTO readByClientId(Long clientId, Long requestId);

    List<ProductRequestDTO> readAll();

    List<ProductRequestDTO> readAllByClientId(Long id);

    boolean update(Long id, RequestDTO requestDTO);

    boolean delete(Long id);
}
