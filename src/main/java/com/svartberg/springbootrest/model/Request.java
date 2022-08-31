package com.svartberg.springbootrest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String secretWord;

    private StatusType status;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "request_product",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(date, request.date) && Objects.equals(secretWord, request.secretWord) && status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, secretWord, status);
    }
}
