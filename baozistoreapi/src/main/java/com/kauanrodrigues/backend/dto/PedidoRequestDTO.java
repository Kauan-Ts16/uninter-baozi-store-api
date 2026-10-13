
package com.kauanrodrigues.baozistoreapi.dto;

public record PedidoRequestDTO(
        Long clienteId,
        Long produtoId,
        Integer quantidade
) {
}
