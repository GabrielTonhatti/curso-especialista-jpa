package com.algawors.ecommerce.mapeamentobasico;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Cliente;
import com.algawors.ecommerce.model.SexoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    void testarEnum() {
        Cliente cliente = new Cliente();
        cliente.setId(4);
        cliente.setNome("José Mineiro");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificacao);
        assertEquals(SexoCliente.MASCULINO, clienteVerificacao.getSexo());
    }
}
