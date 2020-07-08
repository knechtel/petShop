package com.micheliknechtel.petshop.data.repository;


import com.micheliknechtel.petshop.domain.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AnimalRepositoryIntegrationTest {



    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testFindByName() {

        // setup data scenario
        Animal aAnimal = new Animal();
        aAnimal.setName("Mia");

        // save test data
        entityManager.persist(aAnimal);

        // Find an inserted record
        Animal foundAnimal= animalRepository.findByName("Mia");

        assertThat(foundAnimal.getName(), is(equalTo("Mia")));
    }


}
