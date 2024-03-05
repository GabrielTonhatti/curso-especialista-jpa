package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    void usarExpressaoCondicionalLike() {
        String jpql = "SELECT c FROM Cliente c WHERE c.nome LIKE CONCAT('%', :nome, '%')";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido .class);
        typedQuery.setParameter("nome", "a");

        List<Pedido > lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
