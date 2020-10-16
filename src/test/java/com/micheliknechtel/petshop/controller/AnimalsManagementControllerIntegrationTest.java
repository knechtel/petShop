package com.micheliknechtel.petshop.controller;

import com.micheliknechtel.petshop.domain.Animal;
import com.micheliknechtel.petshop.controller.AnimalsManagementController;


import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalsManagementControllerIntegrationTest {

    @Autowired
    public AnimalsManagementController animalsManagementController;

    @Test
    public void testAddAnimalHappyPath() {

        Animal aAnimal = new Animal();
        aAnimal.setName("Oliva");

        // POST our Animal form bean to the controller; check the outcome
        String outcome = animalsManagementController.processAddAnimalSubmit(aAnimal);

        // Assert THAT the outcome is as expected
        assertThat(outcome,is(equalTo("success")));
    }

    @Test
    public void testAddAnimalNameMissing() {

        Animal aAnimal = new Animal();
        // POST our Animal form bean to the controller; check the outcome

        String outcome = animalsManagementController.processAddAnimalSubmit(aAnimal);

        // Assert THAT the outcome is as expected
        assertThat(outcome,is(equalTo("failure")));
    }

}
