package com.myfood.myfoodback.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfood.myfoodback.models.Produtos;
import com.myfood.myfoodback.repositories.ProdutosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosRepository produtosRepository;

    public ProdutosController(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> getAllProdutos() {
        try {
            List<Produtos> produtos = produtosRepository.findAll();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getProdutoById(@PathVariable Long id) {
        Produtos produto = produtosRepository.findById(id).orElse(null);
        if (produto != null) {
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Produtos produto = produtosRepository.findById(id).orElse(null);
        if (produto != null && produto.getImagem() != null) {
            return ResponseEntity.ok().body(produto.getImagem());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Produtos> addProduto(@RequestParam("produto") String produtoStr,
            @RequestParam("file") MultipartFile file) {
        try {
            Produtos produto = new ObjectMapper().readValue(produtoStr, Produtos.class);
            produto.setImagem(file.getBytes());
            Produtos savedProduto = produtosRepository.save(produto);
            return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Produtos> updateProduto(@PathVariable Long id,
            @RequestParam("produto") String updatedProdutoStr, @RequestParam("file") MultipartFile file) {
        try {
            Produtos updatedProduto = new ObjectMapper().readValue(updatedProdutoStr, Produtos.class);
            updatedProduto.setImagem(file.getBytes());
            Produtos existingProduto = produtosRepository.findById(id).orElse(null);
            if (existingProduto != null) {
                updatedProduto.setId(id);
                Produtos savedProduto = produtosRepository.save(updatedProduto);
                return new ResponseEntity<>(savedProduto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Produtos produto = produtosRepository.findById(id).orElse(null);
        if (produto != null) {
            produtosRepository.delete(produto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
