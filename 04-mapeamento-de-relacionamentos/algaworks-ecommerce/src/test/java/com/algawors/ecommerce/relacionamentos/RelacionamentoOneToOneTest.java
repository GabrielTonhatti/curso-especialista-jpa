package com.algawors.ecommerce.relacionamentos;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.PagamentoCartao;
import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.model.StatusPagamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumero("1234");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getPagamento());
    }
}
