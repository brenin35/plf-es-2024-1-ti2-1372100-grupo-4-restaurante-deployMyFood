package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.ItemPedido;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido not found with id " + id));
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        for (ItemPedido item : pedido.getItensPedido()) {
            item.setPedido(pedido);
        }
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }
}
