package com.algawors.ecommerce.model;

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

    @Column(name = "PRODUTO_ID")
    private Integer produtoId;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
