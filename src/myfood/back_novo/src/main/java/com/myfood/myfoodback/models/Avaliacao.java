package com.myfood.myfoodback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produtoId;
    private Integer estrelas;
    private String comentario;

    public Avaliacao() {
    }

    public Avaliacao(Long produtoId, Integer estrelas, String comentario) {
        this.produtoId = produtoId;
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Integer estrelas) {
        this.estrelas = estrelas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
