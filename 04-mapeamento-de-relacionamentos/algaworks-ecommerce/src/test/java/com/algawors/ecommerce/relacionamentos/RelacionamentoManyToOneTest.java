package com.algawors.ecommerce.relacionamentos;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Cliente;
import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getCliente());
    }
}
