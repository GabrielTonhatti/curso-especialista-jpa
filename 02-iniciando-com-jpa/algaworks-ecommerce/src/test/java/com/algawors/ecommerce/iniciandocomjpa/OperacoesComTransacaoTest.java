package com.algawors.ecommerce.iniciandocomjpa;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    void mostrarDiferencaPersistMerge() {
        Produto produtoPersist = new Produto();
        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setPreco(new BigDecimal(2000));
        produtoPersist.setDescricao("O processador mais rápido.");

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        assertNotNull(produtoVerificacaoPersist);

//        ----------------------------------------------
        Produto produtoMerge = new Produto();
        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setPreco(new BigDecimal(2000));
        produtoMerge.setDescricao("O melhor da categoria.");

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        assertNotNull(produtoVerificacaoMerge);
    }

    @Test
    void inserirObjetoComMerge() {
        Produto produto = new Produto();
        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setPreco(new BigDecimal(1000));
        produto.setDescricao("A melhor qualidade de som");

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao);
    }

    @Test
    void atualizarObjetoGerenciado() {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());
    }

    @Test
    void atualizarObjeto() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao);
        assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    void removerObjeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

//        entityManager.clear(); Não é necessário na asserção para operação de remoção

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        assertNull(produtoVerificacao);
    }

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
