package com.rest.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private Long codigo;
	
	private String nome;
	
	@Column(name="codigo_barras")
	private String codigoBarras;
	
	private LocalDateTime tempo;
	
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Produto(Long codigo, String nome, String codigoBarras, LocalDateTime tempo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.codigoBarras = codigoBarras;
		this.tempo = tempo;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
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
