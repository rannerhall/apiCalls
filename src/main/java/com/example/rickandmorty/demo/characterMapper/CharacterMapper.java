package com.example.rickandmorty.demo.characterMapper;

import com.example.rickandmorty.demo.model.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public List<Character> mapCharacters(String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(name);
        JsonNode results = jsonNode.path("results");

        return charactersList(results);
    }

    private List<Character> charactersList(JsonNode results) {
        List<Character> charactersList = new ArrayList<>();
        for (JsonNode character : results) {
            Character rickAndMorty = new Character();
            String name = "name";
            JsonNode id = character.path("id");
            if (!id.isMissingNode()) {
                rickAndMorty.setId(id.asLong());
            }
            JsonNode characterName = character.path(name);
            if (!characterName.isMissingNode()) {
                rickAndMorty.setName(characterName.asText());
            }
            JsonNode status = character.path("status");
            if (!status.isMissingNode()) {
                rickAndMorty.setStatus(status.asText());
            }
            JsonNode species = character.path("species");
            if (!species.isMissingNode()) {
                rickAndMorty.setSpecies(species.asText());
            }
            JsonNode gender = character.path("gender");
            if (!gender.isMissingNode()) {
                rickAndMorty.setGender(gender.asText());
            }
            JsonNode origin = character.path("origin");
            JsonNode originName = origin.path(name);
            if (!originName.isMissingNode()) {
                rickAndMorty.setOriginName(originName.asText());
            }
            JsonNode originUrl = origin.path("url");
            if (!originUrl.isMissingNode()) {
                rickAndMorty.setOriginUrl(originUrl.asText());
            }
            JsonNode location = character.path("location");
            JsonNode locationName = location.path(name);
            if (!locationName.isMissingNode()) {
                rickAndMorty.setLocationName(locationName.asText());
            }
            JsonNode image = character.path("image");
            if (!image.isMissingNode()) {
                rickAndMorty.setImage(image.asText());
            }
            charactersList.add(rickAndMorty);
        }
        return charactersList;
    }
}
