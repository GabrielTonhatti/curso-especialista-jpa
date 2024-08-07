package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

class OperacoesEmLoteTest extends EntityManagerTest {

    private static final int LIMITE_INSERCOES = 4;

    @Test
    void remocoesEmLote() {
        entityManager.getTransaction().begin();

        String jpql = """
                DELETE Produto p
                WHERE p.id BETWEEN 8 AND 12
                """;;

        Query query = entityManager.createQuery(jpql);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Test
    void atualizarEmLote() {
        entityManager.getTransaction().begin();

//        String jpql = """
//                UPDATE Produto p
//                SET p.preco = p.preco + 1
//                WHERE id BETWEEN 1 AND 10
//                """;
        String jpql = """
                UPDATE Produto p
                SET p.preco = p.preco + (p.preco * :percentual)
                WHERE EXISTS (SELECT 1
                              FROM p.categorias c
                              WHERE c.id = :categoriaId)
                """;;

        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoriaId", 2);
        query.setParameter("percentual", BigDecimal.valueOf(0.1));
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Test
    void inserirEmLote() {
        InputStream in = OperacoesEmLoteTest.class.getClassLoader().getResourceAsStream("produtos/importar.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        entityManager.getTransaction().begin();

        int contadorInsersoes = 0;

        for (String linha : reader.lines().collect(Collectors.toUnmodifiableList())) {
            if (linha.isBlank()) {
                continue;
            }

            String[] produtoColuna = linha.split(";");
            Produto produto = new Produto();
            produto.setNome(produtoColuna[0]);
            produto.setDescricao(produtoColuna[1]);
            produto.setDataCriacao(LocalDateTime.now());
            produto.setPreco(new BigDecimal(produtoColuna[2]));

            entityManager.persist(produto);

            if (++contadorInsersoes == LIMITE_INSERCOES) {
                entityManager.flush();
                entityManager.clear();

                contadorInsersoes = 0;

                System.out.println("-------------------------------------");
            }
        }

        entityManager.getTransaction().commit();
    }
}
