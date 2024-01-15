package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESTOQUE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
