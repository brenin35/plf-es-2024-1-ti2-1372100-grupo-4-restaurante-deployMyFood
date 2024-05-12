package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Mesas;
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

    public MesasController(MesasRepository mesasRepository) {
        this.mesasRepository = mesasRepository;
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
