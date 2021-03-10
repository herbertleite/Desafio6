package br.com.supera.game.store.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.service.ProductService;

@RestController
@RequestMapping("/api/Product")
public class ProductController {

	@Autowired
	private ProductService productService;

	// consulta produtos por nome
	@GetMapping("/ByName")
	public ResponseEntity<List<Product>> consultarPorNome(@RequestParam String nome) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByNameContaining(nome));
	}

	// consulta produtos por intervalo de valores
	@GetMapping("/ByPrice")
	public ResponseEntity<List<Product>> consultarPorPreco(@RequestParam BigDecimal precoInicio,
			@RequestParam BigDecimal precoFim) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findByPriceBetween(precoInicio, precoFim));
	}
}
