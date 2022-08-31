package com.svartberg.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestDTO {

    @JsonProperty("secretWord")
    private String secretWord;

    @JsonProperty("status")
    private String status;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("products_id")
    private List<Long> productId;

    @JsonProperty("date")
    private Date date;
}
