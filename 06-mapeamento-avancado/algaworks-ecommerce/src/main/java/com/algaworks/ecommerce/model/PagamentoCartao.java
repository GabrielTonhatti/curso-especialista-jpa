package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO_CARTAO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoCartao {

    @Id
    @Column(name = "PEDIDO_ID")
    @EqualsAndHashCode.Include
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "NUMERO")
    private String numero;
}
