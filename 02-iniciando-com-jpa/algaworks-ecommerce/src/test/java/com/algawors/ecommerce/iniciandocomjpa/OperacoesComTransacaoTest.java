package com.algawors.ecommerce.iniciandocomjpa;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    void inserirOPrimeiroObjeto() {
        Produto produto = new Produto();
        produto.setId(2);
        produto.setNome("Câmera Canon");
        produto.setPreco(new BigDecimal(5000));
        produto.setDescricao("A melhor definição para suas fotos");

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao);
    }

    @Test
    void abrirEFecharATransacao() {
//        Produto produto = new Produto(); // Somente para o método não mostrar erro

        entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

        entityManager.getTransaction().commit();
    }
}
