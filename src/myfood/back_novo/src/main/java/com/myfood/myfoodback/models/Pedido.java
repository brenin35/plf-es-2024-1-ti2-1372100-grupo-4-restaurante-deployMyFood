package com.myfood.myfoodback.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id_pedido", nullable = false)
    private Clientes clienteIdPedido;

    @ManyToOne
    @JoinColumn(name = "mesa_id_pedido", nullable = false)
    private Mesas mesaIdPedido;

    public boolean statusPreparo;
    public boolean statusPagamento;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtoId;


    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Clientes getClienteIdPedido() {
        return clienteIdPedido;
    }

    public void setClienteIdPedido(Clientes clienteIdPedido) {
        this.clienteIdPedido = clienteIdPedido;
    }

    public Mesas getMesaIdPedido() {
        return mesaIdPedido;
    }

    public void setMesaIdPedido(Mesas mesaIdPedido) {
        this.mesaIdPedido = mesaIdPedido;
    }

    public Produtos getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produtos produtoId) {
        this.produtoId = produtoId;
    }

    public boolean isStatusPreparo() {
        return this.statusPreparo;
    }

    public boolean getStatusPreparo() {
        return this.statusPreparo;
    }

    public void setStatusPreparo(boolean statusPreparo) {
        this.statusPreparo = statusPreparo;
    }

    public boolean isStatusPagamento() {
        return this.statusPagamento;
    }

    public boolean getStatusPagamento() {
        return this.statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
    
}