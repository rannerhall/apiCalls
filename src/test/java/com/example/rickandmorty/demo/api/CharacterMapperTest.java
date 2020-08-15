package com.example.rickandmorty.demo.api;

import com.example.rickandmorty.demo.characterMapper.CharacterMapper;
import com.example.rickandmorty.demo.model.Character;
import com.example.rickandmorty.demo.utils.JsonToTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CharacterMapperTest {

    private CharacterMapper rickAndMortyMapper = new CharacterMapper();
    private JsonToTest jsonToTest = new JsonToTest();

    @Test
    void character_name_is_correct_success() throws JsonProcessingException {
        assertEquals("Rick Sanchez", getRickAndMorty().get(0).getName());
    }

    @Test
    void character_name_is_incorrect_failure() throws JsonProcessingException {
        assertNotEquals("Bird person", getRickAndMorty().get(0).getName());
    }


    private List<Character> getRickAndMorty() throws JsonProcessingException {
        String rickAndMortyJson = jsonToTest.json;

        return rickAndMortyMapper.mapCharacters(rickAndMortyJson);
    }
}
