package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PRODUTO")
@EntityListeners({GenericoListener.class})
public class Produto extends EntidadeBaseInteger {

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "DATA_CRIACAO", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ULTIMA_ATUALIZACAO", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "PRODUTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID")
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @Column(name = "TAG")
    @CollectionTable(name = "PRODUTO_TAG", joinColumns = @JoinColumn(name = "PRODUTO_ID"))
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "PRODUTO_ATRIBUTO", joinColumns = @JoinColumn(name = "PRODUTO_ID"))
    private List<Atributo> atributos;

    @Lob
    @Column(name = "FOTO", length = 1000)
    private byte[] foto;
}
