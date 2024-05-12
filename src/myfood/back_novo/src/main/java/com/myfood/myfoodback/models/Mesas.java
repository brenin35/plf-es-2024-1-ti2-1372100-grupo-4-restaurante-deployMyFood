package com.myfood.myfoodback.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeMesa;

    @OneToMany(mappedBy = "mesaId", cascade = CascadeType.ALL)
    private List<Clientes> clientes;

    public Mesas() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMesa() {
        return this.nomeMesa;
    }

    public void setNomeMesa(String nomeMesa) {
        this.nomeMesa = nomeMesa;
    }

    public List<Clientes> getClientes() {
        return this.clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

}
