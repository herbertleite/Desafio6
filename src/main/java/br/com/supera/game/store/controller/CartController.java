package br.com.supera.game.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.model.Cart;
import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.service.CartService;

@RestController
@RequestMapping("/api/Cart")
public class CartController {

	@Autowired
	private CartService cartService;

	// adiciona um produto no carrinho
	@PostMapping("/{id}")
	public ResponseEntity<Cart> adicionaProduto(@PathVariable Long id, @RequestBody Product p) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.adicionarProdutoNoCarrrinho(id, p));
	}

	// exclui um produto do carrinho
	@DeleteMapping("/{id}")
	public ResponseEntity<Cart> removeProduto(@PathVariable Long id, @RequestBody Product p) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.removerProdutoDoCarrrinho(id, p));
	}
	

	// lista os produtos do carrinho por preço
	@GetMapping("ByPreco/{id}")
	public ResponseEntity<List<Product>> organizaPorPreco(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.ordenaPorPreco(id));
	}

	// lista os produtos do carrinho por score
	@GetMapping("ByScore/{id}")
	public ResponseEntity<List<Product>> organizaPorScore(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.ordenaPorScore(id));
	}

	// lista os produtos do carrinho por ordem alfabetica do nome
	@GetMapping("ByName/{id}")
	public ResponseEntity<List<Product>> organizaPorNome(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.ordenaPorNome(id));
	}

}
