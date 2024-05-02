package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NamedQueryTest extends EntityManagerTest {

    @Test
    void executarConsultarDinamica() {
        Produto produto = new Produto();
        produto.setNome("Câmera GoPro");

        List<Produto> lista = pesquisar(produto);

        assertFalse(lista.isEmpty());
        assertEquals("Câmera GoPro Hero 7", lista.get(0).getNome());
    }

    private List<Produto> pesquisar(Produto produto) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Produto p WHERE 1 = 1");

        if (produto.getNome() != null) {
            jpql.append(" AND p.nome LIKE CONCAT('%', :nome, '%')");
        }

        if (produto.getDescricao() != null) {
            jpql.append(" AND p.descricao LIKE CONCAT('%', :descricao, '%')");
        }

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql.toString(), Produto.class);

        if (produto.getNome() != null) {
            typedQuery.setParameter("nome", produto.getNome());
        }

        if (produto.getDescricao() != null) {
            typedQuery.setParameter("descricao", produto.getDescricao());
        }

        return typedQuery.getResultList();
    }

}
