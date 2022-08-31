package com.svartberg.springbootrest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
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
    @ToString.Exclude
    @JsonBackReference
    private Set<Request> request;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(site, client.site) && Objects.equals(comment, client.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, site, comment);
    }
}
