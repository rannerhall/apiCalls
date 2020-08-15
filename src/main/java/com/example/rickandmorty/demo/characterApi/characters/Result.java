package com.example.rickandmorty.demo.characterApi.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private Long id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
}
