package com.myfood.myfoodback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String contatoCliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesas mesa;

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

}
