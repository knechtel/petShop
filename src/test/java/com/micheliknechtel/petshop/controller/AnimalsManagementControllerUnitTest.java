package com.micheliknechtel.petshop.controller;

import com.micheliknechtel.petshop.domain.Animal;
import com.micheliknechtel.petshop.service.AnimalsManagementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AnimalsManagementController.class)
public class AnimalsManagementControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalsManagementService animalsManagementService;

    @InjectMocks
    private AnimalsManagementController animalsManagementController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactHappyPath() throws Exception {
        // setup mock Contact returned the mock service component
        Animal mockAnimal = new Animal();
        mockAnimal.setName("Popeye");

        when(animalsManagementService.add(any(Animal.class))).thenReturn(mockAnimal);

        // simulate the form bean that would POST from the web page
        Animal aAnimal = new Animal();
        aAnimal.setName("Popeye");
        aAnimal.setGender("male");

        // simulate the form submit (POST)
        mockMvc.perform(post("/addAnimal", aAnimal))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testAddAnimalBizServiceRuleNotSatisfied() throws Exception {
        // setup a mock response of NULL object returned from the mock service component
        when(animalsManagementService.add(any(Animal.class)))
                .thenReturn(null);

        // simulate the form bean that would POST from the web page
        Animal aAnimal = new Animal();
        aAnimal.setGender("male");

        // simulate the form submit (POST)
        mockMvc.perform(post("/addAnimal", aAnimal))
                .andExpect(status().is(302))
                .andReturn();
    }

}
