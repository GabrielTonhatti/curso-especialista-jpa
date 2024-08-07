package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NamedQueryTest extends EntityManagerTest {

    @Test
    void executarConsultarArquivoXMLEspecificoProduto() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.todos", Produto.class);
        List<Produto> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());
    }

    @Test
    void executarConsultarArquivoXMLEspecificoPedido() {
        TypedQuery<Pedido> typedQuery = entityManager.createNamedQuery("Pedido.todos", Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());
    }

    @Test
    void executarConsultarArquivoXML() {
        TypedQuery<Pedido> typedQuery = entityManager.createNamedQuery("Pedido.listar", Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());
    }

    @Test
    void executarConsultar() {
        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.listarPorCategoria", Produto.class);
        typedQuery.setParameter("categoriaId", 8);
        List<Produto> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());
    }

}
