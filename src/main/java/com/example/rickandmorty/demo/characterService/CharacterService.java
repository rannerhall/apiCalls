package com.example.rickandmorty.demo.characterService;

import com.example.rickandmorty.demo.characterApi.CharacterApi;
import com.example.rickandmorty.demo.characterApi.CharacterApiPojo;
import com.example.rickandmorty.demo.characterApi.characters.CharactersResponse;
import com.example.rickandmorty.demo.characterApi.characters.Result;
import com.example.rickandmorty.demo.characterRepository.CharacterRepository;
import com.example.rickandmorty.demo.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository rickAndMortyRepository;

    private CharacterApiPojo characterApiPojo = new CharacterApiPojo();
    private CharacterApi characterApi = new CharacterApi();

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
                setCharacter(character);
                characterList.add(setCharacter(character));
            }
            saveCharacters(characterList);
        }
    }

    private Character setCharacter(Result character) {
        Character rickAndMorty = new Character();
        rickAndMorty.setId(character.getId());
        rickAndMorty.setName(character.getName());
        rickAndMorty.setStatus(character.getStatus());
        rickAndMorty.setSpecies(character.getSpecies());
        rickAndMorty.setGender(character.getGender());
        rickAndMorty.setOriginName(character.getOrigin().getName());
        rickAndMorty.setOriginUrl(character.getOrigin().getUrl());
        rickAndMorty.setLocationName(character.getLocation().getName());
        rickAndMorty.setImage(character.getImage());
        return rickAndMorty;
    }

    private void saveCharacters(List<Character> characterList) {
        for (Character character : characterList) {
            rickAndMortyRepository.save(character);
        }
    }
}
