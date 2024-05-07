package com.myfood.myfoodback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myfood.myfoodback.models.Produtos;
import com.myfood.myfoodback.repositories.ProdutosRepository;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAllProdutos() {
        List<Produtos> produtos = produtosRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produtos> addProduto(@RequestBody Produtos produto) {
        Produtos savedProduto = produtosRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> updateProduto(@PathVariable Long id, @RequestBody Produtos produto) {
        if (!produtosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Produtos updatedProduto = produtosRepository.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (!produtosRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
