package com.myfood.myfoodback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.CascadeType;

@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String contatoCliente;

    private Long mesaId;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    public Clientes() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getContatoCliente() {
        return this.contatoCliente;
    }

    public void setContatoCliente(String contatoCliente) {
        this.contatoCliente = contatoCliente;
    }

    public Long getMesaId() {
        return this.mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

}

