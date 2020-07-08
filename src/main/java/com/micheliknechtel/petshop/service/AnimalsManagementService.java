package com.micheliknechtel.petshop.service;

import com.micheliknechtel.petshop.data.repository.AnimalRepository;
import com.micheliknechtel.petshop.domain.Animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalsManagementService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal add(Animal aAnimal) {

        Animal newAnimal = animalRepository.save(aAnimal);

        return newAnimal;
    }

}
