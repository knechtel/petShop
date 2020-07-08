package com.micheliknechtel.petshop.controller;

import com.micheliknechtel.petshop.domain.Animal;


import com.micheliknechtel.petshop.service.AnimalsManagementService;
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

    @RequestMapping(value = "/addAnimal", method = RequestMethod.POST)
    public String processAddAnimalSubmit(@ModelAttribute Animal aAnimal)
    {
        Animal newAnimal = animalsManagementService.add(aAnimal);
        if (newAnimal!= null)
        {
            return  "success";
        }
        return "redirect:/showAddAnimal";
    }
}
