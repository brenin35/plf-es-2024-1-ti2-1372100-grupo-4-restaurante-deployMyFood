package com.myfood.myfood.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double preco;
    private String imagem;
    private boolean visibilidadeAvaliacao;

    // Constructors, getters, and setters

    public Produtos() {
    }

    public Produtos(String nome, String descricao, double preco, String imagem, boolean visibilidadeAvaliacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.visibilidadeAvaliacao = visibilidadeAvaliacao;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public boolean isVisibilidadeAvaliacao() {
        return visibilidadeAvaliacao;
    }

    public void setVisibilidadeAvaliacao(boolean visibilidadeAvaliacao) {
        this.visibilidadeAvaliacao = visibilidadeAvaliacao;
    }
}
