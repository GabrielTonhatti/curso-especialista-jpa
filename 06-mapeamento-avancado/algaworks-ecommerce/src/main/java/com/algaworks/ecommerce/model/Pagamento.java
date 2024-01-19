package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO")
public abstract class Pagamento extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
