package com.svartberg.springbootrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
    @ToString.Exclude
    private Set<Request> requests = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.weight, weight) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(comment, product.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, comment);
    }
}
