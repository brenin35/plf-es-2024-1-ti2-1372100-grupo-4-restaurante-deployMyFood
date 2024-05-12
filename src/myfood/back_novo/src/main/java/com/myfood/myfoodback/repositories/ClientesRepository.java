package com.myfood.myfoodback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfood.myfoodback.models.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
