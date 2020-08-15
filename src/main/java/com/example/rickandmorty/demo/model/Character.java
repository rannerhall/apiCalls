package com.example.rickandmorty.demo.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "species")
    private String species;
    @Column(name = "gender")
    private String gender;
    @Column(name = "origin_name")
    private String originName;
    @Column(name = "origin_url")
    private String originUrl;
    @Column(name = "location_name")
    private String locationName;
    @Column(name = "image")
    private String image;
}
