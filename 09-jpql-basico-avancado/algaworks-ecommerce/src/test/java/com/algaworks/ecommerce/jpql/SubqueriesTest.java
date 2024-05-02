package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SubqueriesTest extends EntityManagerTest {

    @Test
    void pesquisarComAny() {
        // Podemos usar o ANY ou o SOME
        // Todos os produtos que já foram vendedidos por um preço diferente do atual.
        String jpql = """
                SELECT p
                FROM Produto p
                WHERE p.preco <> ANY (SELECT precoProduto from ItemPedido WHERE produto = p)
                """;
//        String jpql = """
//                SELECT p
//                FROM Produto p
//                WHERE p.preco <> SOME (SELECT precoProduto from ItemPedido WHERE produto = p)
//                """;

        // Todos os produtos que já foram vendedidos, pelo menos, uma vez pelo preço atual.
//        String jpql = """
//                SELECT p
//                FROM Produto p
//                WHERE p.preco = ANY (SELECT precoProduto from ItemPedido WHERE produto = p)
//                """;

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(produto -> System.out.printf("ID: %d, Nome: %s%n", produto.getId(), produto.getNome()));
    }

    @Test
    void pesquisarComAll() {
        // Todos os produtos não foram vendidos mais depois que encareceram.
        String jpql = """
                SELECT p
                FROM Produto p
                WHERE p.preco > ALL (SELECT precoProduto from ItemPedido WHERE produto = p)
                """;
//        String jpql = """
//                SELECT p
//                FROM Produto p
//                WHERE p.preco > (SELECT MAX(precoProduto) from ItemPedido WHERE produto = p)
//                """;

        // Todos os produtos que sempre foram vendidos pelo preço atual.
//        String jpql = """
//                SELECT p
//                FROM Produto p
//                WHERE p.preco = ALL (SELECT precoProduto from ItemPedido WHERE produto = p)
//                """;

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(produto -> System.out.printf("ID: %d, Nome: %s%n", produto.getId(), produto.getNome()));
    }

    @Test
    void exercicioPesquisarSubqueriesComExists() {
        String jpql = """
        SELECT p
        FROM Produto p
        WHERE EXISTS (SELECT 1
                      FROM ItemPedido
                      WHERE produto = p
                         AND precoProduto <> p.preco)
        """;

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(produto -> System.out.printf("ID: %d, Nome: %s%n", produto.getId(), produto.getNome()));
    }

    @Test
    void exercicioPesquisarSubqueries() {
        // Bons Clientes. Versão 2.
        String jpql = """
                SELECT c
                FROM Cliente c
                WHERE 2 <= (SELECT COUNT(p.id)
                           FROM Pedido p
                           WHERE p.cliente = c)
                """;

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(cliente -> System.out.printf("ID: %d, Nome: %s%n", cliente.getId(), cliente.getNome()));
    }

    @Test
    void exercicioPesquisarComIN() {
        String jpql = """
               SELECT p
               FROM Pedido p
               WHERE p.id IN (SELECT p2.id
                              FROM ItemPedido i2
                                       INNER JOIN i2.pedido p2
                                       INNER JOIN i2.produto pro2
                                       INNER JOIN pro2.categorias c
                              WHERE c.id = 2)
               """;

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(pedido -> System.out.printf("ID: %d%n", pedido.getId()));
    }

    @Test
    void pesquisarComExists() {
        String jpql = """
                SELECT p
                FROM Produto p
                WHERE EXISTS (SELECT 1
                              FROM ItemPedido ip2
                                       INNER JOIN ip2.produto p2
                              WHERE p2 = p)
                """;

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(produto -> System.out.printf("ID: %d, Nome: %s%n", produto.getId(), produto.getNome()));
    }

    @Test
    void pesquisarComIN() {
        String jpql = """
               SELECT p
               FROM Pedido p
               WHERE p.id IN (SELECT p2.id
                              FROM ItemPedido i2
                                       INNER JOIN i2.pedido p2
                                       INNER JOIN i2.produto pro2
                              WHERE pro2.preco > 100)
               """;

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(pedido -> System.out.printf("ID: %d%n", pedido.getId()));
    }

    @Test
    void pesquisarSubqueries() {
        // Bons Clientes. Versão 2.
        String jpql = """
                SELECT c
                FROM Cliente c
                WHERE 500 < (
                    SELECT SUM(p.total)
                    FROM Pedido p
                    WHERE p.cliente = c
                )
                """;
        // Bons Clientes. Versão 1.
//        String jpql = """
//                SELECT c
//                FROM Cliente c
//                WHERE 500 < (
//                    SELECT SUM(p.total)
//                    FROM c.pedidos p
//                )
//                """;

        // Todos os pedidos acima da média de vendas.
//        String jpql = """
//                SELECT p
//                FROM Pedido p
//                WHERE p.total > (SELECT AVG(total) FROM Pedido)
//                """;

        // O produto ou os produtos mais caros da base de dados.
//        String jpql = """
//                SELECT p
//                FROM Produto p
//                WHERE p.preco = (SELECT MAX(preco) FROM Produto)
//                """;

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

//        lista.forEach(produto -> System.out.printf("ID: %d, Nome: %s, Preço: %.2f%n",
//                produto.getId(), produto.getNome(), produto.getPreco()));
//        lista.forEach(pedido -> System.out.printf("ID: %d, Total: %.2f%n", pedido.getId(), pedido.getTotal()));
        lista.forEach(cliente -> System.out.printf("ID: %d, Nome: %s%n", cliente.getId(), cliente.getNome()));
    }
}
