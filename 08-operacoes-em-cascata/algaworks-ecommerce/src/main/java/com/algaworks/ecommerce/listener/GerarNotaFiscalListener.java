package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;
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
