package com.svartberg.springbootrest.service.impl;

import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Override
    public void create() {

    }
    @Override
    public Client read() {
        return null;
    }

    @Override
    public List<Client> readAll() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
