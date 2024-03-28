package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    void usarExpressaoIN() {
//        List<Integer> parametros = List.of(1, 3, 4);
//        String jpql = "SELECT p "
//                + "FROM Pedido p "
//                + "WHERE p.id IN (:ids)";

        Cliente cliente1 = new Cliente(1); // entityManager.find(Cliente.class, 1);
        Cliente cliente2 = new Cliente(2); // entityManager.find(Cliente.class, 2);

        List<Cliente> clientes = List.of(cliente1, cliente2);
        String jpql = "SELECT p "
                + "FROM Pedido p "
                + "WHERE p.cliente IN (:clientes)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("clientes", clientes);

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void usarExpressaoCase() {
//        String jpql = "SELECT p.id, "
//                + "CASE p.status "
//                + "     WHEN 'PAGO' THEN 'Está pago' "
//                + "     WHEN 'CANCELADO' THEN 'Foi cancelado' "
//                + "     ELSE 'Está aguardando' "
//                + "END "
//                + "FROM Pedido p";
        String jpql = "SELECT p.id, "
                + "CASE TYPE(p.pagamento) "
                + "     WHEN PagamentoBoleto THEN 'Pago com boleto' "
                + "     WHEN PagamentoCartao THEN 'Pago com cartão' "
                + "     ELSE 'Não pago ainda.' "
                + "END "
                + "FROM Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.printf("%s, %s%n", array[0], array[1]));
    }

    @Test
    void usarExpressaoDiferente() {
        String jpql = "SELECT p FROM Produto p WHERE p.preco <> 100";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

    @Test
    void usarBetween() {
        String jpql = "SELECT p FROM Pedido p WHERE p.dataCriacao BETWEEN :dataInicial AND :dataFinal";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }

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
        String jpql = "SELECT p FROM Pedido p WHERE p.dataCriacao >= :data";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("data", LocalDateTime.now().minusDays(2));

        List<Pedido> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());
    }
}
