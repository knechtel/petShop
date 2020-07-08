package com.micheliknechtel.petshop;
import com.micheliknechtel.petshop.controller.AnimalsManagementControllerIntegrationTest;
import com.micheliknechtel.petshop.data.repository.AnimalRepositoryIntegrationTest;
import com.micheliknechtel.petshop.service.AnimalsManagementServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ AnimalsManagementServiceIntegrationTest.class,
        AnimalsManagementControllerIntegrationTest.class, AnimalRepositoryIntegrationTest.class })


public class AddAnimalFeatureTestSuite {
    // don't worry it meant to be empty !!!
}
