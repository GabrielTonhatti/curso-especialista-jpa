package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AbordagemHibridaTest extends EntityManagerTest {

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
        EntityManager em = entityManagerFactory.createEntityManager();
        String jpql = """
                SELECT c FROM Categoria c
                """;
        TypedQuery<Categoria> typedQuery = em.createQuery(jpql, Categoria.class);

        entityManagerFactory.addNamedQuery("Categoria.listar", typedQuery);
    }

    @Test
    void usarAbordagemHibrida() {
        TypedQuery<Categoria> typedQuery = entityManager.createNamedQuery("Categoria.listar", Categoria.class);
        List<Categoria> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());
    }
}
