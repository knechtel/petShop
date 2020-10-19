package com.micheliknechtel.petshop.data.repository;


import com.micheliknechtel.petshop.domain.Animal;
import com.micheliknechtel.petshop.domain.Puppy;
import com.micheliknechtel.petshop.rule.DataSourceConfiguration;
import com.micheliknechtel.petshop.rule.WithEntityManager;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {TestApplicationContext.class, DataSourceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnimalRepositoryIntegrationTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    @Rule
    public WithEntityManager withEntityManager;

    @Test
    public void testFindByName() {
        SQLStatementCountValidator.reset();
        withEntityManager.doInTransaction(() -> {

            Animal aAnimal = new Animal();
            aAnimal.setName("Mia");
            animalRepository.save(aAnimal);
            aAnimal.setGender("Female");
            aAnimal.addPuppy(new Puppy("Puppy 1"));
            aAnimal.addPuppy(new Puppy("Puppy 2"));
            aAnimal.addPuppy(new Puppy("Puppy 3"));
        });
        SQLStatementCountValidator.assertInsertCount(4);

        withEntityManager.doInTransaction(() -> {
            Animal foundAnimal= animalRepository.findByName("Mia");

            assertThat(foundAnimal.getName(), is(equalTo("Mia")));
            assertThat(foundAnimal.getId(), is(notNullValue()));
            assertThat(foundAnimal.getGender(), is("Female"));
            assertThat(foundAnimal.getPuppies().size(), is(3));
            Assertions.assertThat(foundAnimal.getPuppies().stream().map(Puppy::getName).collect(Collectors.toList())).contains("Puppy 1", "Puppy 2", "Puppy 3");
        });
        SQLStatementCountValidator.assertSelectCount(1);
    }

}
