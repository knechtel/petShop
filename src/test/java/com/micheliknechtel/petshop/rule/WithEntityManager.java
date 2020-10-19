package com.micheliknechtel.petshop.rule;

import org.junit.After;
import org.junit.rules.ExternalResource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.transaction.TestTransaction;

@Component
public class WithEntityManager extends ExternalResource {

    @Override
    @After
    public void after() {
        super.after();
    }

    public void doInTransaction(final Runnable consumer) {
        if (TestTransaction.isActive()) {
            TestTransaction.end();
        }
        TestTransaction.start();
        consumer.run();
        TestTransaction.flagForCommit();
        TestTransaction.end();
    }

}
