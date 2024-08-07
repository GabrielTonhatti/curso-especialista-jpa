package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class OperadoresLogicosTest extends EntityManagerTest {

    @Test
    void usarOperadores() {
        String jpql = "SELECT p FROM Pedido p WHERE (p.status = 'AGUARDANDO' OR p.status = 'PAGO') AND p.total > 100";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
