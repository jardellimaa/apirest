package com.rest.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperatura")
public class TemperaturaResource {
	
	@CrossOrigin
	@GetMapping(value="/CparaF/{temperatura}", produces={"application/json"})
	public String converterCparaF(@PathVariable("temperatura") float temperatura) {
		return String.valueOf((temperatura*1.8)+32);
	}
	
	@CrossOrigin
	@GetMapping(value="/FparaC/{temperatura}")
	public ResponseEntity<?> converterFparaC(@PathVariable("temperatura") float temperatura) {
		return ResponseEntity.ok((temperatura-32)/1.8);
	}

}
