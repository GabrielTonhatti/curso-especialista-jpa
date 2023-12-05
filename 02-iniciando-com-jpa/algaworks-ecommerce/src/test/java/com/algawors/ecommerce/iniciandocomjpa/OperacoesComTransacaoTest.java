package com.algawors.ecommerce.iniciandocomjpa;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    void abrirEFecharATransacao() {
        Produto produto = new Produto();

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
