package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAGAMENTO_BOLETO")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "CODIGO_BARRAS")
    private String codigoBarras;

}
