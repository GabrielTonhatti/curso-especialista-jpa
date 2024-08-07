package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "NOTA_FISCAL")
public class NotaFiscal extends EntidadeBaseInteger {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTA_FISCAL_PEDIDO"))
//    @JoinTable(name = "PEDIDO_NOTA_FISCAL",
//            joinColumns = @JoinColumn(name = "NOTA_FISCAL_ID", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "PEDIDO_ID", unique = true)
//    )
    private Pedido pedido;

    @Lob
    @Column(name = "XML", length = 1000, nullable = false)
    private byte[] xml;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_EMISSAO", nullable = false)
    private Date dataEmissao;

}
