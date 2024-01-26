package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    void testarEstrategiaChave() {
        Categoria categoria = new Categoria();
        categoria.setNome("Natação");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
         assertNotNull(categoriaVerificacao);
         assertEquals("Natação", categoriaVerificacao.getNome());
    }

}
