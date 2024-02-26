package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JoinTest extends EntityManagerTest {

    @Test
    void usarJoinFetch() {
        String jpql = "SELECT p FROM Pedido p "
                + "LEFT JOIN FETCH p.pagamento "
                + "JOIN FETCH p.cliente "
                + "LEFT JOIN FETCH p.notaFiscal ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> pedidos = typedQuery.getResultList();
        assertFalse(pedidos.isEmpty());
    }

    @Test
    void fazerLeftJoin() {
        String jpql = "SELECT p FROM Pedido p LEFT OUTER JOIN p.pagamento pag ON pag.status = 'PROCESSANDO'";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void fazerJoin() {
        String jpql = "SELECT p FROM Pedido p INNER JOIN p.pagamento pag";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
