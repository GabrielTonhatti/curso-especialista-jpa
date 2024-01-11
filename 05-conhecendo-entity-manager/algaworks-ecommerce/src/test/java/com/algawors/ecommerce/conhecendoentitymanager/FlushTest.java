package com.algawors.ecommerce.conhecendoentitymanager;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlushTest extends EntityManagerTest {

    @Test
    void chamarFlush() {
        assertThrows(Exception.class, this::erroAoChamarFlush);
//        erroAoChamarFlush();
    }

    private void erroAoChamarFlush() {
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();

            if (pedido.getPagamento() == null) {
                throw new RuntimeException("Pedido ainda não foi pago.");
            }

//            Uma consulta obriga o JPA a sincronizar o que ele tem na memória
//            Pedido pedidoPago2 = entityManager
//                    .createQuery("SELECT P FROM Pedido P WHERE P.id = 1", Pedido.class)
//                            .getSingleResult();
//            assertEquals(pedido.getStatus(), pedidoPago2.getStatus());
            
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            throw e;
        }
    }
}
