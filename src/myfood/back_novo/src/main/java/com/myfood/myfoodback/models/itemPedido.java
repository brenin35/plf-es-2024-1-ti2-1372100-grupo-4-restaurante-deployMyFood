package com.myfood.myfoodback.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

public class itemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    Long quantidade;
    float precoTotal;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtoId;

    public itemPedido() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Produtos getProdutoId() {
        return this.produtoId;
    }

    public void setProdutoId(Produtos produtoId) {
        this.produtoId = produtoId;
    }

    public float getPrecoTotal() {
        return this.precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

}
