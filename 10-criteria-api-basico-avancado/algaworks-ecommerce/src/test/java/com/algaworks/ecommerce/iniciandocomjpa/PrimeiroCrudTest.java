package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    void inserirRegistro() {
        Cliente cliente = new Cliente();
//        cliente.setId(3); Comentado porque estamos utilizando IDENTITY
        cliente.setNome("Carlos Finotti");
        cliente.setCpf("222");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificacao);
    }

    @Test
    void consultandoRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        assertNotNull(cliente);
        assertEquals("Fernando Medeiros", cliente.getNome());
    }

    @Test
    void removendoRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 2);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
        assertNull(clienteVerificacao);
    }

    @Test
    void atualizandoRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        cliente.setNome("João Pedro");
        cliente.setCpf("333");
        cliente.setSexo(SexoCliente.MASCULINO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertEquals("João Pedro", clienteVerificacao.getNome());
    }
}
