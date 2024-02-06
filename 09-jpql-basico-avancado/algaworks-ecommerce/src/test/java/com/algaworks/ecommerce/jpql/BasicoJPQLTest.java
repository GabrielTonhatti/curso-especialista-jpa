package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BasicoJPQLTest extends EntityManagerTest {

    @Test
    void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.id = 1", Pedido.class);

        // SELECT P.* FROM PEDIDO P WHERE P.ID = 1

        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        assertFalse(lista.isEmpty());
    }
}
