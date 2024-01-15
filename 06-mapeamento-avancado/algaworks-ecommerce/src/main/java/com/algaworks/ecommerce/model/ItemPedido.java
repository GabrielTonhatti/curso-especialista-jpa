package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ITEM_PEDIDO")
@IdClass(ItemPedidoId.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @Id
    @Column(name = "PEDIDO_ID")
    @EqualsAndHashCode.Include
    private Integer pedidoId;

    @Id
    @Column(name = "PRODUTO_ID")
    @EqualsAndHashCode.Include
    private Integer produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUTO_ID", insertable = false, updatable = false)
    private Produto produto;

    @Column(name = "PRECO_PRODUTO")
    private BigDecimal precoProduto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
