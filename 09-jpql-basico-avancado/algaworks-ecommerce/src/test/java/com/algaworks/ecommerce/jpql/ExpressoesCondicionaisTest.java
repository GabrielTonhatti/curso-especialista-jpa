package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    void usarIsNull() {
        String jpql = "SELECT p FROM Produto p WHERE p.foto IS NULL";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void usarIsEmpty() {
        String jpql = "SELECT p FROM Produto p WHERE p.categorias IS EMPTY";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void usarExpressaoCondicionalLike() {
        String jpql = "SELECT c FROM Cliente c WHERE c.nome LIKE CONCAT('%', :nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("nome", "a");

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
