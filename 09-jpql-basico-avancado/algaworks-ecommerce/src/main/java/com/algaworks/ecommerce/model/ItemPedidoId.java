package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoId implements Serializable {

    @Column(name = "PEDIDO_ID")
    @EqualsAndHashCode.Include
    private Integer pedidoId;

    @Column(name = "PRODUTO_ID")
    @EqualsAndHashCode.Include
    private Integer produtoId;

}
