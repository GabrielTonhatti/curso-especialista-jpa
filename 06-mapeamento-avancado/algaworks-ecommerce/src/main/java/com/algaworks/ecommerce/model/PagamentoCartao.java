package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@DiscriminatorValue("CARTAO")
@Table(name = "PAGAMENTO_CARTAO")
public class PagamentoCartao extends Pagamento {

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;
}
