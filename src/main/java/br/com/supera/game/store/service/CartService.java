package br.com.supera.game.store.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.exception.ObjNaoEncontradoException;
import br.com.supera.game.store.model.Cart;
import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.model.User;
import br.com.supera.game.store.repository.CartRepository;
import br.com.supera.game.store.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	public Cart adicionarProdutoNoCarrrinho(Long idUser, Product p) {
		User u = procuraPorId(idUser);
		u.getCart().getProducts().add(p);
		return cartRepository.save(u.getCart());
	}

	public Cart removerProdutoDoCarrrinho(Long idUser, Product p) {
		User u = procuraPorId(idUser);
		Set<Product> produtos = u.getCart().getProducts();
		if (produtos.contains(p)) {
			produtos.remove(p);
		}
		return cartRepository.save(u.getCart());
	}

	public List<Product> ordenaPorPreco(Long idUser) {
		User u = procuraPorId(idUser);
		List<Integer> precos = new ArrayList<>();
		for (Product p : u.getCart().getProducts()) {
			precos.add(p.getPrice().intValue());
		}
		Collections.sort(precos);
		List<Product> produtos = new ArrayList<>();
		for (Integer i : precos) {
			for (Product p : u.getCart().getProducts()) {
				if (p.getPrice().intValue() == i)
					produtos.add(p);
			}
		}
		return produtos;
	}

	public List<Product> ordenaPorScore(Long idUser) {
		User u = procuraPorId(idUser);
		List<Short> scores = new ArrayList<>();
		for (Product p : u.getCart().getProducts()) {
			scores.add(p.getScore());
		}
		Collections.sort(scores);
		List<Product> produtos = new ArrayList<>();
		for (Short s : scores) {
			for (Product p : u.getCart().getProducts()) {
				if (p.getScore() == s)
					produtos.add(p);
			}
		}
		return produtos;
	}

	public List<Product> ordenaPorNome(Long idUser) {
		User u = procuraPorId(idUser);
		List<String> nomes = new ArrayList<>();
		for (Product p : u.getCart().getProducts()) {
			nomes.add(p.getName());
		}
		Collections.sort(nomes);
		List<Product> produtos = new ArrayList<>();
		for (String s : nomes) {
			for (Product p : u.getCart().getProducts()) {
				if (p.getName().equalsIgnoreCase(s))
					produtos.add(p);
			}
		}
		return produtos;
	}

	public User procuraPorId(Long Id) {
		Optional<User> obj = userRepository.findById(Id);
		return obj.orElseThrow(() -> new ObjNaoEncontradoException(Id));
	}

}
