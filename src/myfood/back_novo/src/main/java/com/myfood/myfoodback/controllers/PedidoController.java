package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable Long id, @RequestBody Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setClienteIdPedido(pedidoDetails.getClienteIdPedido());
            pedido.setMesaIdPedido(pedidoDetails.getMesaIdPedido());
            pedido.setStatusPreparo(pedidoDetails.isStatusPreparo());
            pedido.setStatusPagamento(pedidoDetails.isStatusPagamento());
            pedido.setPrecoTotalPedido(pedidoDetails.getPrecoTotalPedido());
            pedido.setItensPedido(pedidoDetails.getItensPedido());
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }
}