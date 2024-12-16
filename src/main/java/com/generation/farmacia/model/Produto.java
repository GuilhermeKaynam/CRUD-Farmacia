package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O nome do produto não pode ser nulo.")
	@Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres.")
	@Column(nullable = false)
	private String nome;

	@NotNull(message = "O preço do produto não pode ser nulo.")
	@DecimalMin(value = "0.01", message = "O preço do produto deve ser maior que 0.")
	@Column(nullable = false)
	private BigDecimal preco;

	@ManyToOne(optional = false)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@Column(nullable = false)
	private Integer quantidadeEstoque;

	@Column(nullable = false)
	private LocalDate validade;

	public Produto() {
	}

	public Produto(String nome, BigDecimal preco, Categoria categoria, Integer quantidadeEstoque, LocalDate validade) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
		this.quantidadeEstoque = quantidadeEstoque;
		this.validade = validade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public void ajustarEstoque(int quantidade) {
		this.quantidadeEstoque += quantidade;
	}

	@Override
	public String toString() {
		return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco + ", categoria=" + categoria + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Produto produto = (Produto) o;
		return id.equals(produto.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
