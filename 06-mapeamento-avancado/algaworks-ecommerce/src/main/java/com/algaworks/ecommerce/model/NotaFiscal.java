package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "NOTA_FISCAL")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotaFiscal {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "PEDIDO_ID")
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
//    @JoinTable(name = "PEDIDO_NOTA_FISCAL",
//            joinColumns = @JoinColumn(name = "NOTA_FISCAL_ID", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "PEDIDO_ID", unique = true)
//    )
    private Pedido pedido;

    @Lob
    @Column(name = "XML", length = 1000)
    private byte[] xml;

    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;

}
