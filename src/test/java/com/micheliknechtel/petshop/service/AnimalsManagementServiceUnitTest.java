package com.micheliknechtel.petshop.service;

import com.micheliknechtel.petshop.data.repository.AnimalRepository;
import com.micheliknechtel.petshop.domain.Animal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

        // Create an animal
        Animal mockAnimal = new Animal();
        mockAnimal.setName("Nougat");
        mockAnimal.setGender("Female");

        when(animalRepository.save(any(Animal.class))).thenReturn(mockAnimal);

        // Save the animal
        Animal aAnimal = animalsManagementService.add(null);

        // Verify the save
        assertEquals("Nougat", aAnimal.getName());
    }
}
