package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    void usarContextoPersistencia() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal("100.0"));

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal("10.0"));
        produto2.setDescricao("Boa caneca para café");
        entityManager.persist(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Caneca para chá");
        produto3.setPreco(new BigDecimal("10.0"));
        produto3.setDescricao("Boa caneca para chá");
        produto3 = entityManager.merge(produto3);

        produto3.setDescricao("Alterar descrição");

        entityManager.getTransaction().commit();
    }
}
