package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
}
