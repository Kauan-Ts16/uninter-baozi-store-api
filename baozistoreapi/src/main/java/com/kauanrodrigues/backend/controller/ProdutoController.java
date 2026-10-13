
package com.kauanrodrigues.baozistoreapi.controller;

import com.kauanrodrigues.baozistoreapi.model.ProdutoModel;
import com.kauanrodrigues.baozistoreapi.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criar(@RequestBody ProdutoModel produto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criar(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
