package com.algawors.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CLIENTE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

}
