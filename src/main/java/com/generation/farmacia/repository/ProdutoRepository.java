package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.farmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByNomeContainingIgnoreCase(String nome);

	List<Produto> findByCategoriaId(Long categoriaId);

	List<Produto> findByNomeContainingIgnoreCaseAndCategoriaIdAndPrecoBetween(String nome, Long categoriaId,
			BigDecimal precoMinimo, BigDecimal precoMaximo);

	List<Produto> findByQuantidadeEstoqueLessThan(int quantidade);

	List<Produto> findByValidadeBefore(LocalDate data);

}