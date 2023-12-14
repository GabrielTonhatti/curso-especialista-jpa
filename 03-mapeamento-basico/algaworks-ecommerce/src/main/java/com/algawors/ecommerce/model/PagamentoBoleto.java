package com.algawors.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO_BOLETO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoBoleto {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PEDIDO_ID")
    private Integer pedidoId;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "CODIGO_BARRAS")
    private String codigoBarras;

}
