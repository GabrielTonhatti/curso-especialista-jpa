package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    void usarMaiorMenor() {
        String jpql = "SELECT p FROM Produto p WHERE p.preco >= :precoInicial AND p.preco <= :precoFinal";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("precoInicial", new BigDecimal(400));
        typedQuery.setParameter("precoFinal", new BigDecimal(1500));

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

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

    @Test
    void buscarPedidosDe2DiasAtras() {
        String jpql = "SELECT p FROM Pedido p WHERE p.dataCriacao >= :dataInicial AND p.dataCriacao <= :dataFinal";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
