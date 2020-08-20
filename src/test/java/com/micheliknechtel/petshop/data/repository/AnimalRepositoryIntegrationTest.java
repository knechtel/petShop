package com.micheliknechtel.petshop.data.repository;


import com.micheliknechtel.petshop.domain.Animal;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityTransaction;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = TestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnimalRepositoryIntegrationTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    @Rule
    public WithEntityManager withEntityManager;

    @Test
    public void testFindByName() {
        // setup data scenario
        withEntityManager.doInTransaction(em -> {

            Animal aAnimal = new Animal();
            aAnimal.setName("Mia");
            animalRepository.save(aAnimal);
            aAnimal.setGender("Female");
        });

        withEntityManager.doInTransaction(em -> {
            Animal foundAnimal= animalRepository.findByName("Mia");

            assertThat(foundAnimal.getName(), is(equalTo("Mia")));
            assertThat(foundAnimal.getId(), is(notNullValue()));
            assertThat(foundAnimal.getGender(), is("Female"));
        });
    }


}
