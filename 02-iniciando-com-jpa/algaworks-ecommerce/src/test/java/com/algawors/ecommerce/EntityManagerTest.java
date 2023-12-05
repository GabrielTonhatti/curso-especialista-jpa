package com.algawors.ecommerce;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class EntityManagerTest {

    protected EntityManager entityManager;
    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @BeforeEach
    public void setup() {
        entityManager = entityManagerFactory.createEntityManager();
    }


    @AfterAll
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void tearDown() {
        entityManager.close();
    }
}
