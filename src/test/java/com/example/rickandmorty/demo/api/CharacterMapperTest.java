package com.example.rickandmorty.demo.api;

import com.example.rickandmorty.demo.characterMapper.CharacterMapper;
import com.example.rickandmorty.demo.model.Character;
import com.example.rickandmorty.demo.utils.JsonToTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class CharacterMapperTest {

    private final CharacterMapper rickAndMortyMapper;
    private final JsonToTest jsonToTest = new JsonToTest();

    @Inject
    public CharacterMapperTest(CharacterMapper rickAndMortyMapper) {
        this.rickAndMortyMapper = rickAndMortyMapper;
    }

    @Test
    void character_name_is_correct_success() throws JsonProcessingException {
        assertEquals("Rick Sanchez", getRickAndMorty().get(0).getName());
    }

    @Test
    void character_name_is_incorrect_failure() throws JsonProcessingException {
        assertNotEquals("Bird person", getRickAndMorty().get(0).getName());
    }

    @Test
    void character_id_is_correct_success() throws JsonProcessingException {
        assertEquals(1L, getRickAndMorty().get(0).getId());
    }

    private List<Character> getRickAndMorty() throws JsonProcessingException {
        String rickAndMortyJson = jsonToTest.json;

        return rickAndMortyMapper.mapCharacters(rickAndMortyJson);
    }
}
