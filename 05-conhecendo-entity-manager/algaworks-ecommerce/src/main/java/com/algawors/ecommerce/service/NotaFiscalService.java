package com.algawors.ecommerce.service;

import com.algawors.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido) {
        System.out.println("Gerando nota para o pedido " + pedido.getId());
    }

}
