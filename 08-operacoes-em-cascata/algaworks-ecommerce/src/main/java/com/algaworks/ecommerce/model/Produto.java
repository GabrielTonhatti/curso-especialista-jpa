package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners({GenericoListener.class})
@Table(name = "PRODUTO",
        indexes = {@Index(name = "IDX_NOME", columnList = "NOME")},
        uniqueConstraints = {@UniqueConstraint(name = "UNQ_NOME", columnNames = {"NOME"})})
public class Produto extends EntidadeBaseInteger {

    @Column(name = "NOME", length = 100, nullable = false) // nome varchar(100) not null
    private String nome;

    @Lob
    @Column(name = "DESCRICAO", length = Length.LONG32)
    private String descricao;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Column(name = "DATA_CRIACAO", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ULTIMA_ATUALIZACAO", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "PRODUTO_ID", nullable = false,
                    foreignKey = @ForeignKey(name = "FK_PRODUTO_CATEGORIA_PRODUTO")),
            inverseJoinColumns = @JoinColumn(name = "CATEGORIA_ID", nullable = false,
                    foreignKey = @ForeignKey(name = "FK_PRODUTO_CATEGORIA_CATEGORIA"))
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @Column(name = "TAG", length = 50, nullable = false)
    @CollectionTable(name = "PRODUTO_TAG", joinColumns = @JoinColumn(name = "PRODUTO_ID"),
            foreignKey = @ForeignKey(name = "FK_PRODUTO_TAG_PRODUTO"))
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "PRODUTO_ATRIBUTO", joinColumns = @JoinColumn(name = "PRODUTO_ID"),
            foreignKey = @ForeignKey(name = "FK_PRODUTO_ATRIBUTO_PRODUTO"))
    private List<Atributo> atributos;

    @Lob
    @Column(name = "FOTO", length = 1000)
    private byte[] foto;
}
