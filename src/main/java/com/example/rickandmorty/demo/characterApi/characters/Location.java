package com.example.rickandmorty.demo.characterApi.characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private String name;
    private String url;
}
