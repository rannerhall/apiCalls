package com.example.rickandmorty.demo.characterService;

import com.example.rickandmorty.demo.characterRepository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.inject.Inject;

@SpringBootTest
class CharacterServiceTest {

    @Inject
    private CharacterService characterService;

    @MockBean
    private CharacterRepository characterRepository;

    @Test
    void name() {
    }
}
