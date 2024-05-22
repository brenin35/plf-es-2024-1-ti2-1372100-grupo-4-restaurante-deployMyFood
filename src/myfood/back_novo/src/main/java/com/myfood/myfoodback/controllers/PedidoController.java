package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Clientes;
import com.myfood.myfoodback.models.ItemPedido;
import com.myfood.myfoodback.models.Mesas;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.ClientesRepository;
import com.myfood.myfoodback.repositories.MesasRepository;
import com.myfood.myfoodback.repositories.PedidoRepository;
import com.myfood.myfoodback.repositories.ProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientesRepository clienteRepository;

    @Autowired
    private MesasRepository mesaRepository;


    @Autowired
    private ProdutosRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Clientes cliente = clienteRepository.findById(pedido.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente not found"));
            Mesas mesa = mesaRepository.findById(pedido.getMesa().getId())
                    .orElseThrow(() -> new RuntimeException("Mesa not found"));

            pedido.setCliente(cliente);
            pedido.setMesa(mesa);

            for (ItemPedido item : pedido.getItensPedido()) {
                item.setPedido(pedido);
                item.setProduto(produtoRepository.findById(item.getProduto().getId())
                        .orElseThrow(() -> new RuntimeException("Produto not found")));
            }

            Pedido savedPedido = pedidoRepository.save(pedido);
            return new ResponseEntity<>(savedPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
