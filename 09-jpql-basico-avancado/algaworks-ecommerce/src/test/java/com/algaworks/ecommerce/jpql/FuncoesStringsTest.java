package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class FuncoesStringsTest extends EntityManagerTest {

    @Test
    void aplicarFuncao() {
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
