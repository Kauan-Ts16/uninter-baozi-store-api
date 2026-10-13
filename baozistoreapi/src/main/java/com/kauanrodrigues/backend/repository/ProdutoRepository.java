package com.kauanrodrigues.baozistoreapi.repository;

import com.kauanrodrigues.baozistoreapi.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
}
