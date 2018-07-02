package com.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
	
	public Produto findByCodigoBarras(String codigoBarras);

	public Optional<Produto> save(Optional<Produto> produto);
	
	public List<Produto> findAllByOrderByCodigoAsc();

}
