package com.micheliknechtel.petshop.data.repository;

import com.micheliknechtel.petshop.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a " +
            "LEFT JOIN FETCH a.puppies " +
            "WHERE a.name =:name")
    Animal findByName(@Param("name") String name);
}
