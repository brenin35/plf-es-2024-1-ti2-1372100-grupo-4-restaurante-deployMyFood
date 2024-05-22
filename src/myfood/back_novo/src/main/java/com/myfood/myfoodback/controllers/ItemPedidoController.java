package com.myfood.myfoodback.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myfood.myfoodback.models.ItemPedido;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.ItemPedidoRepository;
import com.myfood.myfoodback.repositories.PedidoRepository;

@RestController
public class ItemPedidoController {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoController(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @PostMapping("/pedidos/{pedidoId}/itensPedido")
    public ResponseEntity<ItemPedido> createItemPedido(@PathVariable Long pedidoId,
            @RequestBody ItemPedido itemPedido) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

        if (optionalPedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = optionalPedido.get();
        itemPedido.setPedido(pedido);

        ItemPedido savedItemPedido = itemPedidoRepository.save(itemPedido);
        return ResponseEntity.ok(savedItemPedido);
    }
}
