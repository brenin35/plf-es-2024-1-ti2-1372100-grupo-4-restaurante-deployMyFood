package com.myfood.myfoodback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfood.myfoodback.models.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
