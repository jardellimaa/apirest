package com.rest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.api.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
	
	public List<Produto> findAllByOrderByCodigoAsc();
	
	@Query(value = "SELECT * FROM PRODUTO p order by p.codigo desc limit 1", nativeQuery = true)
	public Produto findLast();

}
