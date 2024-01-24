package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIA")
public class Categoria extends EntidadeBaseInteger {

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_PAI_ID")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}
