package com.rest.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CODIGO_PRODUTO")
	@SequenceGenerator(name = "CODIGO_PRODUTO", sequenceName = "SEQ_CODIGO_PRODUTO", allocationSize = 1)
	private Integer codigo;

	@Column(nullable = false)
	private String nome;

	@Column(name = "codigo_barras", nullable = false)
	private String codigoBarras;

	@Column(nullable = false)
	private LocalDateTime tempo;

	public Produto() {
		super();
	}

	public Produto(String nome, String codigoBarras, LocalDateTime tempo) {
		super();
		this.nome = nome;
		this.codigoBarras = codigoBarras;
		this.tempo = tempo;
	}
	
	public Produto(Integer codigo, String nome, String codigoBarras, LocalDateTime tempo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.codigoBarras = codigoBarras;
		this.tempo = tempo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public LocalDateTime getTempo() {
		return tempo;
	}

	public void setTempo(LocalDateTime tempo) {
		this.tempo = tempo;
	}

}
