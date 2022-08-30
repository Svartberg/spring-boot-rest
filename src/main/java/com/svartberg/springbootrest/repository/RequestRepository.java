package com.svartberg.springbootrest.repository;

import com.svartberg.springbootrest.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
