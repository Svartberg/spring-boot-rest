package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.model.Client;

import java.util.List;

public interface ProductService {

    void create();

    Client read();

    List<Client> readAll();

    boolean update();

    boolean delete();
}
