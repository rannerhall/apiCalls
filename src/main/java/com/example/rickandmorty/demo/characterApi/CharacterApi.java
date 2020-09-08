package com.example.rickandmorty.demo.characterApi;

import com.example.rickandmorty.demo.characterApi.characters.CharactersResponse;
import com.example.rickandmorty.demo.characterMapper.CharacterMapper;
import com.example.rickandmorty.demo.model.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class CharacterApi {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CharacterMapper rickAndMortyMapper = new CharacterMapper();

    private <T> ResponseEntity<T> callApi(Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://rickandmortyapi.com/api/character/";
        return restTemplate.getForEntity(URL, classType);
    }

    public List<Character> getCharactersFromApi() {
        try {
            ResponseEntity<String> stringResponseEntity = callApi(String.class);
            if (HttpStatus.OK.equals(stringResponseEntity.getStatusCode())) {
                return rickAndMortyMapper.mapCharacters(stringResponseEntity.getBody());
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
