package com.example.rickandmorty.demo.characterController;

import com.example.rickandmorty.demo.characterService.CharacterService;
import com.example.rickandmorty.demo.model.Character;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CharacterController {

    private final CharacterService rickAndMortyService;

    public CharacterController(CharacterService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping(path = {"/index", "/index.html"})
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(path = "/list-characters")
    public String getAllCharactersFromMappedApi(Model model) {
        rickAndMortyService.requestCharactersFromApi();
        List<Character> getCharacterList = rickAndMortyService.getAllCharacters();
        model.addAttribute("characters", getCharacterList);
        return "list-characters";
    }

    @RequestMapping(path = "/list-characters-pojo")
    public String getAllCharactersFromApiPojo(Model model) {
        rickAndMortyService.requestCharactersFromApiPojo();
        List<Character> getCharacterList = rickAndMortyService.getAllCharacters();
        model.addAttribute("characters", getCharacterList);
        return "list-characters";
    }
}
