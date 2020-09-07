package com.example.rickandmorty.demo.characterService;

import com.example.rickandmorty.demo.characterApi.CharacterApi;
import com.example.rickandmorty.demo.characterApi.CharacterApiPojo;
import com.example.rickandmorty.demo.characterApi.characters.CharactersResponse;
import com.example.rickandmorty.demo.characterApi.characters.Result;
import com.example.rickandmorty.demo.characterRepository.CharacterRepository;
import com.example.rickandmorty.demo.model.Character;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository rickAndMortyRepository;
    private final CharacterApiPojo characterApiPojo;
    private final CharacterApi characterApi;

    @Inject
    public CharacterService(CharacterRepository rickAndMortyRepository, CharacterApiPojo characterApiPojo, CharacterApi characterApi) {
        this.rickAndMortyRepository = rickAndMortyRepository;
        this.characterApiPojo = characterApiPojo;
        this.characterApi = characterApi;
    }

    public List<Character> getAllCharacters() {
        return rickAndMortyRepository.findAll();
    }

    public void requestCharactersFromApi() {
        List<Character> characterList = characterApi.getCharactersFromApi();
        saveCharacters(characterList);
    }

    public void requestCharactersFromApiPojo() {
        Optional<CharactersResponse> root = characterApiPojo.getCharacterFromApiPojo();
        List<Character> characterList = new ArrayList<>();
        if (root.isPresent()) {
            List<Result> results = root.get().getResults();
            for (Result character : results) {
                characterList.add(setCharacter(character));
            }
            saveCharacters(characterList);
        }
    }

    private Character setCharacter(Result character) {
        Character newCharacter = new Character();
        newCharacter.setId(character.getId());
        newCharacter.setName(character.getName());
        newCharacter.setStatus(character.getStatus());
        newCharacter.setSpecies(character.getSpecies());
        newCharacter.setGender(character.getGender());
        newCharacter.setOriginName(character.getOrigin().getName());
        newCharacter.setOriginUrl(character.getOrigin().getUrl());
        newCharacter.setLocationName(character.getLocation().getName());
        newCharacter.setImage(character.getImage());
        return newCharacter;
    }

    private void saveCharacters(List<Character> characterList) {
        for (Character character : characterList) {
            rickAndMortyRepository.save(character);
        }
    }
}
