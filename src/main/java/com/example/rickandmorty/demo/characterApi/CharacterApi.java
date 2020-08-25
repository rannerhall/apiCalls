package com.example.rickandmorty.demo.characterApi;

import com.example.rickandmorty.demo.characterMapper.CharacterMapper;
import com.example.rickandmorty.demo.model.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CharacterApi {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private CharacterApiUrl URL = new CharacterApiUrl();
    private CharacterMapper rickAndMortyMapper = new CharacterMapper();

    private ResponseEntity<String> callApi() {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://rickandmortyapi.com/api/character/";
        return restTemplate.getForEntity(URL, String.class);
    }

    public List<Character> getCharactersFromApi() {
        ResponseEntity<String> stringResponseEntity;
        try {
            ResponseEntity<String> stringResponseEntity = callApi();
            if (HttpStatus.OK.equals(stringResponseEntity.getStatusCode())) {
                return rickAndMortyMapper.mapCharacters(stringResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("", e);
        }
        return new ArrayList<>();
    }
}
