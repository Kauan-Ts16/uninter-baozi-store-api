
package com.kauanrodrigues.baozistoreapi.service;

import com.kauanrodrigues.baozistoreapi.model.ProdutoModel;
import com.kauanrodrigues.baozistoreapi.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoModel criar(ProdutoModel produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public ProdutoModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado!"
                ));
    }

    public void excluir(Long id) {
        ProdutoModel produto = buscarPorId(id);
        repository.delete(produto);
    }
}
