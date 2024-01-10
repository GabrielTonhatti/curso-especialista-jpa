package com.algawors.ecommerce.conhecendoentitymanager;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

class EstadosEClicoDeVidaTest extends EntityManagerTest {

    @Test
    void analisarEstados() {
        Categoria categoriaNovo = new Categoria();

        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        Categoria caregoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(caregoriaGerenciada);
        entityManager.persist(caregoriaGerenciada);

        entityManager.detach(caregoriaGerenciada);
    }
}
