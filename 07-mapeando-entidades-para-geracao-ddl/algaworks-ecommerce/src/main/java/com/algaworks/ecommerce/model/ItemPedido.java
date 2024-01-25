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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    @Column(name = "PRECO_PRODUTO", precision = 19, scale = 2, nullable = false)
    private BigDecimal precoProduto;

    @Column(name = "QUANTIDADE", nullable = false)
    private Integer quantidade;

}
