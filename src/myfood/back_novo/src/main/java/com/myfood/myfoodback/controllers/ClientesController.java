package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Clientes;
import com.myfood.myfoodback.repositories.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesRepository clientesRepository;

    @Autowired
    public ClientesController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping
    public ResponseEntity<List<Clientes>> getAllClientes() {
        List<Clientes> clientes = clientesRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getClienteById(@PathVariable Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Clientes> createCliente(@RequestBody Clientes cliente) {
        Clientes savedCliente = clientesRepository.save(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable Long id) {
        try {
            clientesRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
