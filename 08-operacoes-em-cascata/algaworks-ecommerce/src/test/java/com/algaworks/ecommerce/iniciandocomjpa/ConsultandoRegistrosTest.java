package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    void buscarPorIdentificador() {
        Produto produto = entityManager.find(Produto.class, 1);
//        Produto produto = entityManager.getReference(Produto.class, 1);

        assertNotNull(produto);
        assertEquals("Kindle", produto.getNome());
    }

    @Test
    void atualizarAReferencia() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        entityManager.refresh(produto);

        assertEquals("Kindle", produto.getNome());
    }

}
