package com.myfood.myfoodback.models;

import javax.persistence.*;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantidade;
    private float precoTotal;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtoId;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public ItemPedido() {}

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Produtos getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Produtos produtoId) {
        this.produtoId = produtoId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
