package com.micheliknechtel.petshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "puppy")
public class Puppy {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Animal animal;
    private String name;

    public Puppy() {
    }

    public Puppy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
