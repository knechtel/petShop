package com.micheliknechtel.petshop.service;

import com.micheliknechtel.petshop.data.repository.AnimalRepository;
import com.micheliknechtel.petshop.domain.Animal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AnimalsManagementServiceUnitTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalsManagementService animalsManagementService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactHappyPath() {

        // Create a contact
        Animal mockAnimal = new Animal();
        mockAnimal.setName("Pelucha");
        mockAnimal.setGender("Female");

        when(animalRepository.save(any(Animal.class))).thenReturn(mockAnimal);

        // Save the contact
        Animal aAnimal = animalsManagementService.add(null);

        // Verify the save
        assertEquals("Pelucha", aAnimal.getName());


    }
}
