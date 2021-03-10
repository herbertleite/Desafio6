package br.com.supera.game.store.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.model.User;
import br.com.supera.game.store.service.UserService;

@RestController
@RequestMapping("/api/User")
public class UserController {
	
	@Autowired
	private UserService userService;

	// Busca um usuario por id
	@GetMapping("/{id}")
	public ResponseEntity<User> buscaUserPorId(@PathVariable Long id) {
		User clas = userService.procuraPorId(id);
		return ResponseEntity.ok(clas);
	}

	// Busca todos usuarios
	@GetMapping
	public ResponseEntity<List<User>> buscaTodosUsuarios() {
		return ResponseEntity.ok(userService.procuraTodos());
	}

	// faz checkout
	@GetMapping("Checkout/{id}")
	public ResponseEntity<Map<String, BigDecimal>> checkout(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.checkout(id));
	}

}
