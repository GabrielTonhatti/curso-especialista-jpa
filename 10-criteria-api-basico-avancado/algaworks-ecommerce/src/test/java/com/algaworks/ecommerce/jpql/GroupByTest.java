package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.StatusPedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class GroupByTest extends EntityManagerTest {

    @Test
    void condicionarAgrupamentoComHaving() {
        // Total de vendas dentre as categorias que mais vendem.
        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i "
                + "INNER JOIN i.produto p "
                + "INNER JOIN p.categorias c "
                + "GROUP BY c.id "
                + "HAVING SUM(i.precoProduto) > 100";
//        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i "
//                + "INNER JOIN i.produto p "
//                + "INNER JOIN p.categorias c "
//                + "GROUP BY c.id "
//                + "HAVING AVG(i.precoProduto) > 1500";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.printf("Categoria: %s, Valor: %s%n", array[0], array[1]));
    }


    @Test
    void agruparEFiltrarResultado() {
        // Total de vendas por mês
//        String jpql = "SELECT CONCAT(YEAR(p.dataCriacao), '/', FUNCTION('monthname', p.dataCriacao)) , SUM(p.total) FROM Pedido p "
//                + "WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) "
////                + "WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) AND p.status = :status "
//                + "GROUP BY YEAR(p.dataCriacao), MONTH(p.dataCriacao)";

        // Total de vendas por categoria
//        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i "
//                + "INNER JOIN i.produto pro "
//                + "INNER JOIN pro.categorias c "
//                + "INNER JOIN i.pedido p "
//                + "WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) AND MONTH(p.dataCriacao) = MONTH(CURRENT_DATE) "
//                + "GROUP BY c.id";

        // Total de vendas por cliente
        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i "
                + "INNER JOIN i.pedido p "
                + "INNER JOIN p.cliente c "
                + "WHERE YEAR(p.dataCriacao) = YEAR(CURRENT_DATE) AND MONTH(p.dataCriacao) >= (MONTH(CURRENT_DATE) - 3)"
                + "GROUP BY c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.printf("Data: %s, Valor: %s%n", array[0], array[1]));
    }

    @Test
    void agruparResultado() {
        // Quantidade de produtos por categoria
//        String jpql = "SELECT c.nome, COUNT(p.id) FROM Categoria c INNER JOIN c.produtos p GROUP BY c.id";

        // Total de vendas por mês
//        String jpql = "SELECT CONCAT(YEAR(p.dataCriacao), '/', FUNCTION('monthname', p.dataCriacao)) , SUM(p.total) FROM Pedido p "
//                + "GROUP BY YEAR(p.dataCriacao), MONTH(p.dataCriacao)";

        // Total de vendas por categoria
//        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i INNER JOIN i.produto pro INNER JOIN pro.categorias c GROUP BY c.id";

        // Total de vendas por cliente
//        String jpql = "SELECT c.nome, SUM(i.precoProduto) FROM ItemPedido i INNER JOIN i.pedido p INNER JOIN p.cliente c GROUP BY c.id, p.id";

        // Total de Vendas por dia e por categoria
//        String jpql = "SELECT CONCAT(DAY(p.dataCriacao), '/', MONTH(p.dataCriacao), '/', YEAR(p.dataCriacao)), "
//        + "c.nome FROM ItemPedido i INNER JOIN i.produto p INNER JOIN p.categorias c GROUP BY c.id";
        String jpql = "SELECT " +
                " CONCAT(DAY(p.dataCriacao), '/', MONTH(p.dataCriacao), '/', YEAR(p.dataCriacao)), " +
                " CONCAT(c.nome, ': ', SUM(i.precoProduto)) " +
                " FROM ItemPedido i JOIN i.pedido p JOIN i.produto PRO JOIN PRO.categorias c " +
                " GROUP BY YEAR(p.dataCriacao), MONTH(p.dataCriacao), DAY(p.dataCriacao), c.id " +
                " ORDER BY p.dataCriacao, c.nome ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.printf("Data: %s, Categoria: %s%n", array[0], array[1]));
    }
}
