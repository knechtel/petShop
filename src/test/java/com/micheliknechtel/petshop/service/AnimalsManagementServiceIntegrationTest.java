package com.micheliknechtel.petshop.service;

import com.micheliknechtel.petshop.domain.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AnimalsManagementServiceIntegrationTest {

    @Autowired
    private AnimalsManagementService animalsManagementService;

    @Test
    public void testAddAnimalHappyPath() {

        // Create a contact
        Animal aAnimal = new Animal();
        aAnimal.setName("Mia");
        aAnimal.setGender("female");


        // Test adding the contact
        Animal newAnimal = animalsManagementService.add(aAnimal);

        // Verify the addition
        assertNotNull(aAnimal);
        assertNotNull(aAnimal.getId());
        assertEquals("Mia", aAnimal.getName());

    }

}

