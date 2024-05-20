package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.ItemPedido;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.ItemPedidoRepository;
import com.myfood.myfoodback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<ItemPedido> getAllItemPedidos() {
        return itemPedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ItemPedido getItemPedidoById(@PathVariable Long id) {
        return itemPedidoRepository.findById(id).orElse(null);
    }

    @PostMapping("/{pedidoId}")
    public ItemPedido createItemPedido(@PathVariable Long pedidoId, @RequestBody ItemPedido itemPedido) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        if (pedido != null) {
            itemPedido.setPedido(pedido);
            return itemPedidoRepository.save(itemPedido);
        }
        return null;
    }

    @PutMapping("/{id}")
    public ItemPedido updateItemPedido(@PathVariable Long id, @RequestBody ItemPedido itemPedidoDetails) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
        if (itemPedido != null) {
            itemPedido.setQuantidade(itemPedidoDetails.getQuantidade());
            itemPedido.setPrecoTotal(itemPedidoDetails.getPrecoTotal());
            itemPedido.setProdutoId(itemPedidoDetails.getProdutoId());
            itemPedido.setPedido(itemPedidoDetails.getPedido());
            return itemPedidoRepository.save(itemPedido);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteItemPedido(@PathVariable Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
