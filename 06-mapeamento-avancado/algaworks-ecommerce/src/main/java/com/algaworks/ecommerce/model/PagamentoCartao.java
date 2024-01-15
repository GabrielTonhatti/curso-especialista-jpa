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
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "NUMERO")
    private String numero;
}
