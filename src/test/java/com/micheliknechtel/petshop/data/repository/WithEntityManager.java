package com.micheliknechtel.petshop.data.repository;

import org.junit.After;
import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class WithEntityManager extends ExternalResource {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @After
    public void after() {
        super.after();
    }

    public void doInTransaction(final Consumer<EntityManager> consumer) {
        doInTransaction(em -> {
            consumer.accept(em);
            return null;
        });
    }

    public <R> R doInTransaction(final JPATransactionFunction<R> function) {
        EntityManager entityManager = null;
        EntityTransaction txn = null;
        R result;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            function.beforeTransactionCompletion();
            txn = entityManager.getTransaction();
            txn.begin();
            result = function.apply(entityManager);
            txn.commit();
        } catch (final Throwable e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }
            if (e.getCause() instanceof OptimisticLockException) {
                throw (OptimisticLockException) e.getCause();
            }
            throw e;
        } finally {
            function.afterTransactionCompletion();
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return result;
    }

    @FunctionalInterface
    public interface JPATransactionFunction<R> extends Function<EntityManager, R> {

        default void beforeTransactionCompletion() {
        }

        default void afterTransactionCompletion() {
        }
    }
}
