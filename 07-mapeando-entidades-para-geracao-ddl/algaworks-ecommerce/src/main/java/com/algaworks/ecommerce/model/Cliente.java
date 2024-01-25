package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@SecondaryTable(name = "CLIENTE_DETALHE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "CLIENTE_ID"))
@Table(name = "CLIENTE",
        indexes = {@Index(name = "IDX_NOME", columnList = "NOME")},
        uniqueConstraints = {@UniqueConstraint(name = "UNQ_CPF", columnNames = {"CPF"})})
public class Cliente extends EntidadeBaseInteger {

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "CPF", length = 14, nullable = false)
    private String cpf;

    @ElementCollection
    @Column(name = "DESCRICAO")
    @MapKeyColumn(name = "TIPO")
    @CollectionTable(name = "CLIENTE_CONTATO", joinColumns = @JoinColumn(name = "CLIENTE_ID"))
    private Map<String, String> contatos;

    @Transient
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO", table = "CLIENTE_DETALHE", length = 30, nullable = false)
    private SexoCliente sexo;

    @Column(name = "DATA_NASCIMENTO", table = "CLIENTE_DETALHE")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}
