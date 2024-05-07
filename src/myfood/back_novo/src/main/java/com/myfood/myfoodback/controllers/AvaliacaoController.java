package com.myfood.myfoodback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myfood.myfoodback.models.Avaliacao;
import com.myfood.myfoodback.repositories.AvaliacaoRepository;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAllAvaliacao() {
        List<Avaliacao> Avaliacao = avaliacaoRepository.findAll();
        return ResponseEntity.ok(Avaliacao);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> addProduto(@RequestBody Avaliacao produto) {
        Avaliacao savedProduto = avaliacaoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateProduto(@PathVariable Long id, @RequestBody Avaliacao produto) {
        if (!avaliacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Avaliacao updatedProduto = avaliacaoRepository.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        avaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
