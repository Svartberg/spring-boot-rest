package com.svartberg.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svartberg.springbootrest.model.Client;
import com.svartberg.springbootrest.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductRequestDTO {
    @JsonProperty("secretWord")
    private String secretWord;

    @JsonProperty("status")
    private String status;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("client")
    private Client client;

    @JsonProperty("products")
    private List<Product> products;
}
