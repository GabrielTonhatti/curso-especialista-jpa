package com.algawors.ecommerce.relacionamentos;

import com.algawors.ecommerce.EntityManagerTest;
import com.algawors.ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

class EagerELazyTest extends EntityManagerTest {

    @Test
    void verificarComportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();
    }
}
