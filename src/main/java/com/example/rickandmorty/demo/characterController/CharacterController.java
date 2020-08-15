package com.example.rickandmorty.demo.characterController;

import com.example.rickandmorty.demo.characterService.CharacterService;
import com.example.rickandmorty.demo.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CharacterController {

    @Autowired
    private CharacterService rickAndMortyService;

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

/*
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editTodoItemById(Model model, @PathVariable("id") Optional<Long> itemId) throws RecordNotFoundException {
        if (itemId.isPresent()) {
            Optional<TodoItem> todoItem = todoItemService.getTodoItemById(itemId.get());
            model.addAttribute("todoItem", todoItem);
        } else {
            model.addAttribute("todoItem", new TodoItem());
        }
        return "add-edit-todoItem";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteTodoItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        todoItemService.deleteTodoItemById(id);
        return "redirect:/";
    }

    @PostMapping(path = "/createTodoItem")
    public String createOrUpdateTodoItem(TodoItem todoItem) {
        if (todoItem.getTaskName().isBlank()) {
            return "redirect:/";
        }
        todoItemService.createOrUpdateTodoItem(todoItem);
        return "redirect:/";
    }

 */
}
