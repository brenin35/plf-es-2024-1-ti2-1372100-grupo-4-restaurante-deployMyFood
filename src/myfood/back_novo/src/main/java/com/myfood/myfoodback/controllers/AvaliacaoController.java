package com.myfood.myfoodback.controllers;

import com.myfood.myfoodback.models.Avaliacao;
import com.myfood.myfoodback.models.Produtos;
import com.myfood.myfoodback.repositories.AvaliacaoRepository;
import com.myfood.myfoodback.repositories.ProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    public AvaliacaoController(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAllAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
    try {
        if (avaliacao.getProduto() == null || avaliacao.getProduto().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Long produtoId = avaliacao.getProduto().getId();
        Optional<Produtos> produtoOptional = produtosRepository.findById(produtoId);
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Produtos produto = produtoOptional.get();
        avaliacao.setProduto(produto);

        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.ok(savedAvaliacao);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}


    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElse(null);
        if (avaliacao != null) {
            return new ResponseEntity<>(avaliacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao updatedAvaliacao) {
        Avaliacao existingAvaliacao = avaliacaoRepository.findById(id).orElse(null);
        if (existingAvaliacao != null) {
            updatedAvaliacao.setId(id);
            Avaliacao savedAvaliacao = avaliacaoRepository.save(updatedAvaliacao);
            return new ResponseEntity<>(savedAvaliacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).orElse(null);
        if (avaliacao != null) {
            avaliacaoRepository.delete(avaliacao);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}