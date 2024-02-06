package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIA", uniqueConstraints = {@UniqueConstraint(name = "UNQ_NOME", columnNames = {"NOME"})})
public class Categoria extends EntidadeBaseInteger {

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_PAI_ID", foreignKey = @ForeignKey(name = "FK_CATEGORIA_CATEGORIAPAI"))
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}
