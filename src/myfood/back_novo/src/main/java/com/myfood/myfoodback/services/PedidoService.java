package com.myfood.myfoodback.services;

import com.myfood.myfoodback.models.Clientes;
import com.myfood.myfoodback.models.Mesas;
import com.myfood.myfoodback.models.Pedido;
import com.myfood.myfoodback.models.ItemPedido;
import com.myfood.myfoodback.repositories.ClientesRepository;
import com.myfood.myfoodback.repositories.MesasRepository;
import com.myfood.myfoodback.repositories.PedidoRepository;
import com.myfood.myfoodback.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MesasRepository mesasRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));
    }

    public Pedido createPedido(Pedido pedido) {
        Clientes cliente = clientesRepository.findById(pedido.getClientes().getId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + pedido.getClientes().getId()));
        Mesas mesa = mesasRepository.findById(pedido.getMesas().getId())
                .orElseThrow(() -> new EntityNotFoundException("Mesa não encontrada com id: " + pedido.getMesas().getId()));

        pedido.setClientes(cliente);
        pedido.setMesas(mesa);

        Pedido savedPedido = pedidoRepository.save(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setPedido(savedPedido);
            itemPedidoRepository.save(item);
        }

        return pedidoRepository.findById(savedPedido.getId()).orElseThrow(() -> new EntityNotFoundException("Pedido not found after save"));
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
