package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class DetalhesColumnTest extends EntityManagerTest {

    @Test
    void impedirInsercaoDaColunaAtualizacao() {
        Produto produto = new Produto();
        produto.setNome("Teclado para smartphone");
        produto.setDescricao("O mais confortável");
        produto.setPreco(BigDecimal.ONE);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao.getDataCriacao());
        assertNull(produtoVerificacao.getDataUltimaAtualizacao());
    }

    @Test
    void impedirAtualizacaoDaColunaCriacao() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(BigDecimal.TEN);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}
