package com.algawors.ecommerce.relacionamentos;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

class OptionalTest extends EntityManagerTest {

    @Test
    void verificarComportamento() {
         Pedido pedido = entityManager.find(Pedido.class, 1);
    }

}
