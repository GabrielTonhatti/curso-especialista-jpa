package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO_BOLETO")
public class PagamentoBoleto extends EntidadeBaseInteger {

    @Column(name = "PEDIDO_ID")
    private Integer pedidoId;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "CODIGO_BARRAS")
    private String codigoBarras;

}
