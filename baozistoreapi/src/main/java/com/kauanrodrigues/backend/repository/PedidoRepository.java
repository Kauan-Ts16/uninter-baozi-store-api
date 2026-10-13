package com.kauanrodrigues.baozistoreapi.repository;

import com.kauanrodrigues.baozistoreapi.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
