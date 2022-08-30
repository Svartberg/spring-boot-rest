package com.svartberg.springbootrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private float weight;

    private String comment;

    @ManyToMany(mappedBy = "products")
    private Set<Request> requests = new HashSet<>();



}
