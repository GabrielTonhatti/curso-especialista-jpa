package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class FuncoesTest extends EntityManagerTest {

    @Test
    void aplicarFuncaoNativas() {
        String jpql = "SELECT FUNCTION('dayname', p.dataCriacao) FROM Pedido p WHERE FUNCTION('ACIMA_MEDIA_FATURAMENTO', p.total) = 1";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

//        lista.forEach(pedido -> System.out.println("ID: " + pedido.getId() + ", Total: " + pedido.getTotal()));
        lista.forEach(obj -> System.out.println("Dayname: " + obj));
    }

    @Test
    void aplicarFuncaoColecao() {
        String jpql = "SELECT SIZE(p.itens) FROM Pedido p WHERE SIZE(p.itens) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(size -> System.out.println("QTD: " + size));
    }

    @Test
    void aplicarFuncaoNumero() {
        // ABS - VALOR ABSOLUTO, MOD - RESTO DA DIVISÃO, SQRT - RAIZ QUADRADA
        String jpql = "SELECT ABS(p.total), MOD(p.id, 2), SQRT(p.total) FROM Pedido p WHERE ABS(p.total) > 1000";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.println(array[0] + " | " + array[1] + " | " + array[2]));
    }

    @Test
    void aplicarFuncaoData() {
        // TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        // CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP

//        String jpql = "SELECT YEAR(p.dataCriacao), MONTH(p.dataCriacao), DAY(p.dataCriacao) FROM Pedido p";
        String jpql = "SELECT HOUR(p.dataCriacao), MINUTE(p.dataCriacao), SECOND(p.dataCriacao) FROM Pedido p "
                + "WHERE HOUR(p.dataCriacao) > 18";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.println(array[0] + " | " + array[1] + " | " + array[2]));
    }

    @Test
    void aplicarFuncaoString() {
        // CONCAT, LENGTH, LOCATE, SUBSTRING, LOWER, UPPER, TRIM

//        String jpql = "SELECT c.nome, CONCAT('Categoria: ', c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, LENGTH(c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, LOCATE('a', c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, SUBSTRING(c.nome, 3, 2) FROM Categoria c";
//        String jpql = "SELECT c.nome, LOWER(c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, UPPER(c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, TRIM(c.nome) FROM Categoria c";
//        String jpql = "SELECT c.nome, LENGTH(c.nome) FROM Categoria c WHERE LENGTH(c.nome) > 10";
        String jpql = "SELECT c.nome, LENGTH(c.nome) FROM Categoria c WHERE SUBSTRING(c.nome, 1, 1) = 'N'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(array -> System.out.println(array[0] + " - " + array[1]));
    }
}
