package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.dto.ClientDTO;
import com.svartberg.springbootrest.model.Client;

import java.util.List;

public interface ClientService {

    void create(ClientDTO clientDTO);

    Client read(Long id);

    List<Client> readAll();

    boolean update(Long id, final ClientDTO clientDTO);

    boolean delete(Long id);
}
