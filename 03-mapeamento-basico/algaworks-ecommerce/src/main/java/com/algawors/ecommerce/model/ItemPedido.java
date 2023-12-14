package com.algawors.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ITEM_PEDIDO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PEDIDO_ID")
    private Integer pedidoId;

    @Column(name = "PRODUTO_ID")
    private Integer produtoId;

    @Column(name = "PRECO_PRODUTO")
    private BigDecimal precoProduto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
