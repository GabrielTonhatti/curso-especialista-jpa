package com.algawors.ecommerce.mapeamentobasico;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Cliente;
import com.algawors.ecommerce.model.EnderecoEntregaPedido;
import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    void analisarMapeamentoObjetoEmbutido() {
        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("00000-000");
        endereco.setLogradouro("Rua das Laranjeiras");
        endereco.setNumero("123");
        endereco.setBairro("Centro");
        endereco.setCidade("Uberlândia");
        endereco.setEstado("MG");

        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(endereco);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao);
        assertNotNull(pedidoVerificacao.getEnderecoEntrega());
        assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
    }

}
