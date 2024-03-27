package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class GroupByTest extends EntityManagerTest {

    @Test
    void agruparResultado() {
        // Quantidade de produtos por categoria
//        String jpql = "SELECT c.nome, COUNT(p.id) FROM Categoria c INNER JOIN c.produtos p GROUP BY c.id";

        // Total de vendas por mês
//        String jpql = "SELECT CONCAT(YEAR(p.dataCriacao), '/', FUNCTION('monthname', p.dataCriacao)) , SUM(p.total) FROM Pedido p "
//                + "GROUP BY YEAR(p.dataCriacao), MONTH(p.dataCriacao)";

        // Total de vendas por categoria
        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i JOIN i.produto pro JOIN pro.categorias c GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.printf("Categoria: %s, Quantidade: %s%n", array[0], array[1]));
    }
}
