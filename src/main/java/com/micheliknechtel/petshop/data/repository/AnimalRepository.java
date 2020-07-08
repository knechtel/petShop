package com.micheliknechtel.petshop.data.repository;

import com.micheliknechtel.petshop.domain.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
    Animal findByName(String name);
}
