package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BasicoCriteriaTest extends EntityManagerTest {

    @Test
    void buscarPorIdentificador() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

//        String jpql = """
//                SELECT p FROM Pedido p WHERE p.id = 1
//                """;

//        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);
    }
}
