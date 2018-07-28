package com.rest.api.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.api.model.Produto;
import com.rest.api.repository.Produtos;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private Produtos produtos;
	
	@GetMapping
	public ResponseEntity<List<Produto>> listar(){
		return ResponseEntity.ok(produtos.findAllByOrderByCodigoAsc());
	}
	
	@GetMapping(value="/{codigo}")
	public ResponseEntity<Produto> buscar(@PathVariable("codigo") Integer codigo){
		if (produtos.existsById(codigo)) {
			return ResponseEntity.ok(produtos.findById(codigo).get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value="/last")
	public ResponseEntity<Produto> buscarUltimo(){
		return ResponseEntity.ok(produtos.findLast());
	}
	
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
		produto.setTempo(LocalDateTime.now());
		produto.setCodigo(null);
		produto = produtos.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").
				buildAndExpand(produto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}
	
	@PutMapping(value="/{codigo}")
	public ResponseEntity<Produto> alterar(@PathVariable("codigo") Integer codigo, @RequestBody Produto produto){
		if (produtos.existsById(codigo)) {
			produto.setCodigo(codigo);
			produto.setTempo(LocalDateTime.now());
			return ResponseEntity.accepted().body(produtos.save(produto));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable("codigo") Integer codigo) {
		if (produtos.existsById(codigo)) {
			produtos.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}


}
