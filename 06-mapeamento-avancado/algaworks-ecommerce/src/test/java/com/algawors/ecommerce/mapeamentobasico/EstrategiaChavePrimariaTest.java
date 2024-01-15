package com.algawors.ecommerce.mapeamentobasico;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    void testarEstrategiaChave() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
         assertNotNull(categoriaVerificacao);
         assertEquals("Eletrônicos", categoriaVerificacao.getNome());
    }

}
