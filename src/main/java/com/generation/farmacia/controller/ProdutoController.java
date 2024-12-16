package com.generation.farmacia.controller;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<List<Produto>> listar(@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int tamanho, @RequestParam(defaultValue = "nome") String ordenarPor) {
		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenarPor));
		Page<Produto> produtos = produtoRepository.findAll(pageable);
		return ResponseEntity.ok(produtos.getContent());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		return produtoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/buscar")
	public List<Produto> buscarPorNome(@RequestParam String nome) {
		return produtoRepository.findByNomeContainingIgnoreCase(nome);
	}

	@GetMapping("/estoque-baixo")
	public List<Produto> listarProdutosComEstoqueBaixo(@RequestParam(defaultValue = "10") int limiteEstoque) {
		return produtoRepository.findByQuantidadeEstoqueLessThan(limiteEstoque);
	}

	@GetMapping("/validade-proxima")
	public List<Produto> listarProdutosComValidadeProxima(@RequestParam(defaultValue = "30") int dias) {
		return produtoRepository.findByValidadeBefore(LocalDate.now().plusDays(dias));
	}

	@PostMapping
	public Produto criar(@Valid @RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id).map(produtoExistente -> {
			produtoExistente.setNome(produto.getNome());
			produtoExistente.setPreco(produto.getPreco());
			produtoExistente.setCategoria(produto.getCategoria());
			produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
			produtoExistente.setValidade(produto.getValidade());
			produtoRepository.save(produtoExistente);
			return ResponseEntity.ok(produtoExistente);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}/ajuste-estoque")
	public ResponseEntity<Produto> ajustarEstoque(@PathVariable Long id, @RequestBody AjusteEstoqueRequest request) {
		return produtoRepository.findById(id).map(produtoExistente -> {
			produtoExistente.ajustarEstoque(request.getQuantidade());
			produtoRepository.save(produtoExistente);
			return ResponseEntity.ok(produtoExistente);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}