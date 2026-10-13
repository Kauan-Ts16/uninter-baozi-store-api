
package com.kauanrodrigues.baozistoreapi.service;

import com.kauanrodrigues.baozistoreapi.model.ClienteModel;
import com.kauanrodrigues.baozistoreapi.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteModel criar(ClienteModel cliente) {
        cliente.setId(null);
        return repository.save(cliente);
    }

    public List<ClienteModel> listarTodos() {
        return repository.findAll();
    }

    public ClienteModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente não encontrado!"
                ));
    }

    public void excluir(Long id) {
        ClienteModel cliente = buscarPorId(id);
        repository.delete(cliente);
    }
}
