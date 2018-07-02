package com.rest.api.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private Produtos produtos;
	
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Produto>> listar(){
		//return new ResponseEntity<List<Produto>>(produtos.findAllByOrderByCodigoAsc(), HttpStatus.OK);
		return ResponseEntity.ok(produtos.findAllByOrderByCodigoAsc());
	}
	
	@CrossOrigin
	@GetMapping(value="/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable("codigo") Integer codigo){
		
		Optional<Produto> produto = produtos.findById(codigo);
		
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Produto produto){
		
		LocalDateTime l = (LocalDateTime.now());
		
		produto.setTempo(l);
		produto = produtos.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").
				buildAndExpand(produto.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).body(produto);
	}
	
	@CrossOrigin
	@PutMapping
	public ResponseEntity<Produto> alterar(@RequestBody Produto produto){
		
		if (produtos.findById(produto.getCodigo()).isPresent()) {
			LocalDateTime l = (LocalDateTime.now());
			produto.setTempo(l);
			produto = produtos.save(produto);
			return ResponseEntity.accepted().body(produto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable("codigo") Integer codigo) {
		
		Optional<Produto> produto = produtos.findById(codigo);
		
		if (produto.isPresent()) {
			produtos.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}


}
