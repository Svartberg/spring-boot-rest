package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.dto.RequestDTO;

import java.util.List;

public interface RequestService {

    void create(RequestDTO requestDTO);

    RequestDTO read(Long id);

    List<RequestDTO> readAll();

    List<RequestDTO> readAllByClientId(Long id);

    boolean update(Long id, RequestDTO requestDTO);

    boolean delete(Long id);
}
