package com.micheliknechtel.petshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DatabaseCleaner {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void truncate() {
        List<Optional<String>> entities = entityManager.getMetamodel().getEntities().stream()
                .map(EntityType::getJavaType)
                .map(Class::getName)
                .map(entityClassName -> {
                    try {
                        Annotation[] annotations = Class.forName(entityClassName).getAnnotations();
                        return Stream.of(annotations)
                                .filter(annotation -> annotation.annotationType().getName().equals("javax.persistence.Table"))
                                .map(Table.class::cast)
                                .map(Table::name)
                                .findFirst();
                    } catch (ClassNotFoundException e) {
                        return Optional.<String>empty();
                    }
                })
                .collect(Collectors.toList());

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        entityManager.flush();
        entities.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(entity -> entityManager.createNativeQuery(String.format("TRUNCATE TABLE %s", entity)).executeUpdate());
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
    }
}
