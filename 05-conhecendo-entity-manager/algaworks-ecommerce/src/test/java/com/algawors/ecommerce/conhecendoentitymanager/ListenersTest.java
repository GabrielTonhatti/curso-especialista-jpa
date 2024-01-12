package com.algawors.ecommerce.conhecendoentitymanager;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Cliente;
import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.model.Produto;
import com.algawors.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListenersTest extends EntityManagerTest {

    @Test
    public void carregarEntidades() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);


    }

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
