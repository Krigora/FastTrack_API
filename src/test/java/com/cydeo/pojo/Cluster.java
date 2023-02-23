package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter@ToString
public class Cluster {

   private int id;

   private String name;

   @JsonProperty("rooms")
   private List<Room> roomList;



}
