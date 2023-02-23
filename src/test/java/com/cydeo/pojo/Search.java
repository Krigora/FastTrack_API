package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Search {
    @JsonProperty("content")
    private List<Spartan> allSpartans;
    private int totalElement;

}
