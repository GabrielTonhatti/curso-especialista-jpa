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
    void usarDistinct() {
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
