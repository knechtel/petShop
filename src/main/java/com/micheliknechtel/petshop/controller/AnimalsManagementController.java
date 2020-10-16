package com.micheliknechtel.petshop.controller;

import com.google.gson.GsonBuilder;
import com.micheliknechtel.petshop.domain.Animal;
import com.micheliknechtel.petshop.infrastructure.log.Action;


import com.micheliknechtel.petshop.infrastructure.log.StructuredLog;
import com.micheliknechtel.petshop.service.AnimalsManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnimalsManagementController {

    @Autowired
    private AnimalsManagementService animalsManagementService;

    private static Logger logger = LoggerFactory.getLogger(AnimalsManagementController.class);

    @RequestMapping(value = "/addAnimal", method = RequestMethod.POST)
    public String processAddAnimalSubmit(@ModelAttribute Animal aAnimal)
    {
        logger.info(new StructuredLog(Action.USER, new GsonBuilder().create().toJson(aAnimal)).toJson());

        Animal newAnimal = animalsManagementService.add(aAnimal);
        if (newAnimal.getName()!= null)
        {
            return  "success";
        }

        return "failure";
    }
}
