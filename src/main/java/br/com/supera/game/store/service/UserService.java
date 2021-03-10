package br.com.supera.game.store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.exception.ObjNaoEncontradoException;
import br.com.supera.game.store.model.Cart;
import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.model.User;
import br.com.supera.game.store.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User procuraPorId(Long Id) {
		Optional<User> obj = userRepository.findById(Id);
		return obj.orElseThrow(() -> new ObjNaoEncontradoException(Id));
	}

	public List<User> procuraTodos() {
		return userRepository.findAll();
	}

	public Float subTotaldoUsuario(Long idUser) {
		Optional<User> u = userRepository.findById(idUser);
		Float b = subTotaldoCarrinho(u.get().getCart());
		return b;
	}

	public Float subTotaldoCarrinho(Cart cart) {
		Float total = 0f;
		for (Product p : cart.getProducts()) {
			total = total + p.getPrice().setScale(2, RoundingMode.DOWN).floatValue();
		}
		return total;
	}

	public Float freteDoUsuario(Long idUser) {
		Optional<User> u = userRepository.findById(idUser);
		Float f = freteDoCarrinho(u.get().getCart());
		return f;
	}

	public Float freteDoCarrinho(Cart cart) {
		int qtd = 0;
		Float frete = 0f;
		for (@SuppressWarnings("unused")
		Product p : cart.getProducts()) {
			qtd++;
		}
		frete = frete + qtd * 10;
		return frete;
	}

	public Float totalUsuario(Long idUser) {
		Optional<User> u = userRepository.findById(idUser);
		Float total = freteDoCarrinho(u.get().getCart()) + subTotaldoCarrinho(u.get().getCart());
		return total;
	}

	@SuppressWarnings("deprecation")
	public Map<String, BigDecimal> checkout(Long idUser) {
		User u = procuraPorId(idUser);
		Float total = totalUsuario(u.getId());
		Float frete = freteDoUsuario(u.getId());
		Float subtotal = subTotaldoUsuario(u.getId());
		if (subtotal > 250f) {
			total = total - frete;
			frete = 0f;
		}
		BigDecimal bfrete = new BigDecimal(frete);
		bfrete = bfrete.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal bsub = new BigDecimal(subtotal);
		bsub = bsub.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal btotal = new BigDecimal(total);
		btotal = btotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		return Map.of("frete", bfrete, "subtotal", bsub, "total", btotal);
	}

}
