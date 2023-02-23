package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {

    private int id;

    private String name;

    private int capacity;

}
