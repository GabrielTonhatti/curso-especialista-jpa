package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO")
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
public class Pedido extends EntidadeBaseInteger {

    @ManyToOne(optional = false)
    @JoinColumn(name = "CLIENTE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE"))
    private Cliente cliente;

    @Column(name = "DATA_CRIACAO", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ULTIMA_ATUALIZACAO", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "DATA_CONCLUSAO")
    private LocalDateTime dataConclusao;

    @Column(name = "TOTAL", nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 30, nullable = false)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    public boolean isPago() {
        return status == StatusPedido.PAGO;
    }

    //    @PreUpdate
//    @PrePersist
    public void calcularTotal() {
        if (itens != null) {
            total = itens
                    .stream()
                    .map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir o Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar o Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover o Pedido.");
    }

    @PostLoad
    public void aposCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}
