package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PathExpressionTest extends EntityManagerTest {

    @Test
    void buscarPedidosComProdutoEspecifico() {
        String jpql = "SELECT p FROM Pedido p INNER JOIN p.itens i WHERE i.produto.id = 1";

        TypedQuery<Pedido > typedQuery = entityManager.createQuery(jpql, Pedido .class);

        List<Pedido > lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void usarPathExpression() {
        String jpql = "SELECT p.cliente.nome FROM Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
