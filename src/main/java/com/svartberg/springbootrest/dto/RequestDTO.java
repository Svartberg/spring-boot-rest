package com.svartberg.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svartberg.springbootrest.model.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RequestDTO {

    @JsonProperty("secretWord")
    private String secretWord;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("date")
    private Date date;
}
