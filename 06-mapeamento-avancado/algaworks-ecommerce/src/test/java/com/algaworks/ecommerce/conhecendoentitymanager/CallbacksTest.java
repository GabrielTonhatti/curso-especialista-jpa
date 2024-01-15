package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallbacksTest extends EntityManagerTest {

    @Test
    void acionarCallbacks() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getDataCriacao());
        assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }

}
