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

    private String status;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtoId;

    private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}