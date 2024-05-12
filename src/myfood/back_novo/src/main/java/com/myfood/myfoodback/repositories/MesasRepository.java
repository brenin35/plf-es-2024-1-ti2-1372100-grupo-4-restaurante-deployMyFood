package com.myfood.myfoodback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfood.myfoodback.models.Mesas;

@Repository
public interface MesasRepository extends JpaRepository<Mesas, Long>{
    
}
