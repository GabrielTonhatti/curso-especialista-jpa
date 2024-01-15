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
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "PEDIDO_ID")
//    @JoinTable(name = "PEDIDO_NOTA_FISCAL",
//            joinColumns = @JoinColumn(name = "NOTA_FISCAL_ID", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "PEDIDO_ID", unique = true)
//    )
    private Pedido pedido;

    @Column(name = "XML")
    private String xml;

    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;

}
