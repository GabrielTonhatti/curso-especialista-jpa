package com.algawors.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @Column(name = "DATA_PEDIDO")
    private LocalDateTime dataPedido;

    @Column(name = "DATA_CONCLUSAO")
    private LocalDateTime dataConclusao;

    @Column(name = "NOTA_FISCAL_ID")
    private Integer notaFiscalId;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

}
