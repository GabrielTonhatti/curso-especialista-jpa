package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO_CARTAO")
public class PagamentoCartao extends Pagamento {

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;
}
