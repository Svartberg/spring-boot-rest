package com.svartberg.springbootrest.repository;

import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByClientOrderByIdDesc(Client client);

    List<Request> findRequestsByProductsId(Long productsId);
}
