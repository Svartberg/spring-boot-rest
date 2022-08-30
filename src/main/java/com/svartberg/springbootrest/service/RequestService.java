package com.svartberg.springbootrest.service;

import com.svartberg.springbootrest.model.Client;

import java.util.List;

public interface RequestService {

    void create();

    Client read();

    List<Client> readAll();

    boolean update();

    boolean delete();
}
