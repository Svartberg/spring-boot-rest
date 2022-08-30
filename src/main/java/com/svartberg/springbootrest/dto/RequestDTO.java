package com.svartberg.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.svartberg.springbootrest.model.StatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDTO {

    @JsonProperty("secretWord")
    private String secretWord;

    @JsonProperty("status")
    private StatusType status;
}
