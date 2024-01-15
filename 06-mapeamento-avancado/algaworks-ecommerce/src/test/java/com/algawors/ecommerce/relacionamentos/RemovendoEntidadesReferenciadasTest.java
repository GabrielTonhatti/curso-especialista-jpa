package com.algawors.ecommerce.relacionamentos;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    void removerEntidadeRelacionada() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(itemPedido -> entityManager.remove(itemPedido));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        assertNull(pedidoVerificacao);
    }
}
