package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasicoJPQLTest extends EntityManagerTest {

    @Test
    void projetarNoDTO() {
        String jpql = "SELECT new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) FROM Produto";

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> lista = typedQuery.getResultList();
        assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome()));
    }

    @Test
    void projetarOResultado() {
        String jpql = "SELECT id, nome FROM Produto";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        assertTrue(lista.get(0).length == 2);

        lista.forEach(arr -> System.out.println("ID: " + arr[0] + ", Nome: " + arr[1]));
    }

    @Test
    void selecionarUmAtributoParaRetorno() {
        String jpql = "SELECT p.nome FROM Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();
        assertTrue(String.class.equals(lista.get(0).getClass()));

        String jpqlCliente = "SELECT p.cliente FROM Pedido p";
        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> listaClientes = typedQueryCliente.getResultList();
        assertTrue(Cliente.class.equals(listaClientes.get(0).getClass()));
    }

    @Test
    void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.id = 1", Pedido.class);

        // SELECT P.* FROM PEDIDO P WHERE P.ID = 1

        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        assertFalse(lista.isEmpty());
    }

    @Test
    void mostrarDiferencaQueries() {
        String jpql = "SELECT p FROM Pedido p WHERE p.id = 1";
        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        assertNotNull(pedido2);
//        List<Pedido> lista = query.getResultList();
//        assertFalse(lista.isEmpty());
    }
}
