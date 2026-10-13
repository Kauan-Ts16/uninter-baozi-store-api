
package com.kauanrodrigues.baozistoreapi.service;

import com.kauanrodrigues.baozistoreapi.dto.PedidoRequestDTO;
import com.kauanrodrigues.baozistoreapi.model.ClienteModel;
import com.kauanrodrigues.baozistoreapi.model.PedidoModel;
import com.kauanrodrigues.baozistoreapi.model.ProdutoModel;
import com.kauanrodrigues.baozistoreapi.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(
            PedidoRepository repository,
            ClienteService clienteService,
            ProdutoService produtoService
    ) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public PedidoModel criar(PedidoRequestDTO dto) {

        ClienteModel cliente = clienteService.buscarPorId(
                dto.clienteId()
        );

        ProdutoModel produto = produtoService.buscarPorId(
                dto.produtoId()
        );

        if (dto.quantidade() == null ||
                dto.quantidade() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A quantidade deve ser maior que zero!"
            );
        }

        PedidoModel pedido = new PedidoModel();

        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(dto.quantidade());

        BigDecimal valorTotal = produto.getPreco()
                .multiply(BigDecimal.valueOf(dto.quantidade()));

        pedido.setValorTotal(valorTotal);

        return repository.save(pedido);
    }

    public List<PedidoModel> listarTodos() {
        return repository.findAll();
    }

    public PedidoModel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Pedido não encontrado!"
                        )
                );
    }

    public void excluir(Long id) {
        PedidoModel pedido = buscarPorId(id);
        repository.delete(pedido);
    }
}
