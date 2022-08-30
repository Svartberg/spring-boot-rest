package com.svartberg.springbootrest.repository;

import com.svartberg.springbootrest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
