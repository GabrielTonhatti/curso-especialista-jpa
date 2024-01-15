package com.algawors.ecommerce.conhecendoentitymanager;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Categoria;
import com.algawors.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    void verificarCache() {
        Produto produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("------------------------------");

//        entityManager.close();
//        entityManager = entityManagerFactory.createEntityManager();
        
        Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());
    }
}
