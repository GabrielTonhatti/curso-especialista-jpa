package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassandoParametrosTest extends EntityManagerTest {

    @Test
    void passarParametroDate() {
        String jpql = "SELECT nf FROM NotaFiscal nf WHERE nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal .class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        List<NotaFiscal > lista = typedQuery.getResultList();
        assertEquals(1, lista.size());
    }

    @Test
    void passarParametro() {
        String jpql = "SELECT p FROM Pedido p INNER JOIN p.pagamento pag WHERE p.id = :pedidoId AND pag.status = ?1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido .class);
        typedQuery.setParameter("pedidoId", 2);
        typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

        List<Pedido > lista = typedQuery.getResultList();
        assertEquals(1, lista.size());
    }
}
