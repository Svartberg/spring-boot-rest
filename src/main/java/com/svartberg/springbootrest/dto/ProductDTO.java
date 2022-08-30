package com.svartberg.springbootrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private float weight;

    @JsonProperty("comment")
    private String comment;

}
