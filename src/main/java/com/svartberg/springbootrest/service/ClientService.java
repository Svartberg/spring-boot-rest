package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    void create(ClientDTO clientDTO);

    ClientDTO read(Long id);

    List<ClientDTO> readAll();

    boolean update(Long id, ClientDTO clientDTO);

    boolean delete(Long id);
}
