package com.example.rickandmorty.demo.characterRepository;

import com.example.rickandmorty.demo.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

}
