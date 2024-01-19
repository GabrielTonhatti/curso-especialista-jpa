package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RelacionamentoOneToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumeroCartao("1234");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    void verificarRelacionamentoNotaFiscalPedido() {
        Pedido pedido = entityManager.find(Pedido.class, 1);
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setXml("TESTE".getBytes());
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC)));

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getNotaFiscal());
    }
}
