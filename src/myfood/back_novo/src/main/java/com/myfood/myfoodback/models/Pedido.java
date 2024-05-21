package com.myfood.myfoodback.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id_pedido", nullable = false)
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "mesa_id_pedido", nullable = false)
    private Mesas mesas;

    private boolean statusPreparo;
    private boolean statusPagamento;

    @Column(precision = 10, scale = 2)
    private BigDecimal precoTotalPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedido;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clientes getClientes() {
        return this.clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Mesas getMesas() {
        return this.mesas;
    }

    public void setMesas(Mesas mesas) {
        this.mesas = mesas;
    }

    public boolean getStatusPreparo() {
        return this.statusPreparo;
    }

    public boolean getStatusPagamento() {
        return this.statusPagamento;
    }

    public boolean isStatusPreparo() {
        return statusPreparo;
    }

    public void setStatusPreparo(boolean statusPreparo) {
        this.statusPreparo = statusPreparo;
    }

    public boolean isStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getPrecoTotalPedido() {
        return precoTotalPedido;
    }

    public void setPrecoTotalPedido(BigDecimal precoTotalPedido) {
        this.precoTotalPedido = precoTotalPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
