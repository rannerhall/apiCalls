package com.example.rickandmorty.demo.characterApi;

import com.example.rickandmorty.demo.characterApi.characters.CharactersResponse;
import com.example.rickandmorty.demo.characterMapper.CharacterMapper;
import com.example.rickandmorty.demo.model.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Component
public class CharacterApi {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CharacterMapper characterMapper;
    private final RestTemplate restTemplate;

    @Inject
    public CharacterApi(CharacterMapper rickAndMortyMapper, RestTemplateBuilder builder) {
        this.characterMapper = rickAndMortyMapper;
        this.restTemplate = builder.build();
    }

    private <T> ResponseEntity<T> callApi(Class<T> classType) {
        String URL = "https://rickandmortyapi.com/api/character/";
        return restTemplate.getForEntity(URL, classType);
    }

    public List<Character> getCharactersFromApi() {
        try {
            ResponseEntity<String> stringResponseEntity = callApi(String.class);
            if (HttpStatus.OK.equals(stringResponseEntity.getStatusCode())) {
                return characterMapper.mapCharacters(stringResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("", e);
        }
        return List.of();
    }

    public Optional<CharactersResponse> getCharacterFromApiPojo() {
        try {
            ResponseEntity<CharactersResponse> characterResponseEntity = callApi(CharactersResponse.class);
            if (HttpStatus.OK.equals(characterResponseEntity.getStatusCode())) {
                return Optional.ofNullable(characterResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("", e);
        }
        return Optional.empty();
    }
}
