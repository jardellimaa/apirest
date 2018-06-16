package com.rest.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listar(){
		return new ResponseEntity<List<Produto>>(produtos.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value="/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable("codigo") Long codigo){
		
		Optional<Produto> produto = produtos.findById(codigo);
		
		if (produto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Produto produto){
		
		if (produtos.findById(produto.getCodigo()).isPresent()) {
			return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
		}
		
		produto = produtos.save(produto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{codigo}").
				buildAndExpand(produto.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping (method = RequestMethod.PUT)
	public ResponseEntity<Produto> alterar(@RequestBody Produto produto){
		
		if (produtos.findById(produto.getCodigo()).isPresent()) {
			produto = produtos.save(produto);
						
			return ResponseEntity.accepted().body(produto);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
		
		Optional<Produto> produto = produtos.findById(codigo);
		
		if (produto.isPresent()) {
			produtos.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}


}
