package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ESTOQUE")
public class Estoque extends EntidadeBaseInteger {

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUTO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ESTOQUE_PRODUTO"))
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
