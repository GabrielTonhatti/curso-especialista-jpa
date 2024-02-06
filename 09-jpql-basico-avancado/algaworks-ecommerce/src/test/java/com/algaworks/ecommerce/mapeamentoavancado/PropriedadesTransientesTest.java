package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    void validarPrimeiroNome() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        assertEquals("Fernando", cliente.getPrimeiroNome());
    }
}
