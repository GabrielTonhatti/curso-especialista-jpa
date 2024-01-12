package com.algawors.ecommerce.listener;

import com.algawors.ecommerce.model.Pedido;
import com.algawors.ecommerce.service.NotaFiscalService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private final NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PreUpdate
    @PrePersist
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerar(pedido);
        }
    }

}
