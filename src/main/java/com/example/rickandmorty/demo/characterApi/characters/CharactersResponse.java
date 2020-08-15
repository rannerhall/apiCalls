package com.example.rickandmorty.demo.characterApi.characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CharactersResponse {

    @JsonProperty()
    private List<Result> results = new ArrayList<>();
}
