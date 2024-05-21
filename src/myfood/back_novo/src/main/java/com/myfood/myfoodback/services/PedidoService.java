package com.myfood.myfoodback.services;

import com.myfood.myfoodback.models.Clientes;
import com.myfood.myfoodback.models.Mesas;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.repositories.ClientesRepository;
import com.myfood.myfoodback.repositories.MesasRepository;
import com.myfood.myfoodback.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MesasRepository mesasRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));
    }

    public Pedido createPedido(Long clienteId, Long mesaId) {
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + clienteId));
        Mesas mesa = mesasRepository.findById(mesaId)
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada com id: " + mesaId));

        Pedido pedido = new Pedido();
        pedido.setClientes(cliente);
        pedido.setMesas(mesa);
        pedido.setStatusPreparo(false);
        pedido.setStatusPagamento(false);
        pedido.setPrecoTotalPedido(BigDecimal.ZERO);

        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Long id, Pedido pedidoDetails) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));

        pedido.setClientes(pedidoDetails.getClientes());
        pedido.setMesas(pedidoDetails.getMesas());
        pedido.setStatusPreparo(pedidoDetails.isStatusPreparo());
        pedido.setStatusPagamento(pedidoDetails.isStatusPagamento());
        pedido.setPrecoTotalPedido(pedidoDetails.getPrecoTotalPedido());
        pedido.setItensPedido(pedidoDetails.getItensPedido());

        return pedidoRepository.save(pedido);
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
