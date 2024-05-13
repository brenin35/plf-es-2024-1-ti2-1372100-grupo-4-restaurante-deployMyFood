package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Clientes;
import com.myfood.myfoodback.models.Mesas;
import com.myfood.myfoodback.repositories.ClientesRepository;
import com.myfood.myfoodback.repositories.MesasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
public class MesasController {

    private final MesasRepository mesasRepository;
    private final ClientesRepository clientesRepository;


    public MesasController(MesasRepository mesasRepository, ClientesRepository clientesRepository) {
        this.mesasRepository = mesasRepository;
        this.clientesRepository = clientesRepository;
    }

    @GetMapping
    public ResponseEntity<List<Mesas>> getAllMesas() {
        List<Mesas> mesas = mesasRepository.findAll();
        return ResponseEntity.ok(mesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesas> getMesaById(@PathVariable Long id) {
        Optional<Mesas> mesa = mesasRepository.findById(id);
        return mesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mesas> createMesa(@RequestBody Mesas mesa) {
        Mesas savedMesa = mesasRepository.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMesa);
    }
    //Salvar dados do cliente de acordo com a mesa escolhida
    @PostMapping("/{id}/clientes")
    public ResponseEntity<String> receberDadosCliente(@PathVariable Long id, @RequestBody Clientes cliente) {
        try {
            
            Optional<Mesas> mesaOptional = mesasRepository.findById(id);
            if (!mesaOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            
            Mesas mesa = mesaOptional.get();
            cliente.setMesaId(mesa.getId());

            
            Clientes clienteSalvo = clientesRepository.save(cliente);

            return ResponseEntity.ok("Dados do cliente salvos com sucesso. ID do cliente: " + clienteSalvo.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar os dados do cliente: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
        Optional<Mesas> existingMesaOptional = mesasRepository.findById(id);
        if (existingMesaOptional.isPresent()) {
            mesasRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
