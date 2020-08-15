package com.example.rickandmorty.demo.characterApi;

import com.example.rickandmorty.demo.characterApi.characters.CharactersResponse;
import com.example.rickandmorty.demo.utils.CharacterApiUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class CharacterApiPojo {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private CharacterApiUrl URL = new CharacterApiUrl();

    private ResponseEntity<CharactersResponse> callApiWithPojo() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(URL.getURL(), CharactersResponse.class);
    }

    public Optional<CharactersResponse> getCharacterFromApiPojo() {
        ResponseEntity<CharactersResponse> stringResponseEntity;
        try {
            stringResponseEntity = callApiWithPojo();
            if (HttpStatus.OK.equals(stringResponseEntity.getStatusCode())) {
                return Optional.ofNullable(stringResponseEntity.getBody());
            }
        } catch (Exception e) {
            log.warn("", e);
        }
        return Optional.empty();
    }
}
