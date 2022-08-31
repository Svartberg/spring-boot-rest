package com.svartberg.springbootrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String site;

    private String comment;

    @OneToMany(mappedBy = "client")
    private Set<Request> request;

}
