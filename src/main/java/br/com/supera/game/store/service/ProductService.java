package br.com.supera.game.store.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.model.Product;
import br.com.supera.game.store.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findByNameContaining(String name) {
		return productRepository.findByNameContaining(name);
	}

	public List<Product> findByPriceBetween(BigDecimal precoInicio, BigDecimal precoFim) {
		return productRepository.findByPriceBetween(precoInicio, precoFim);
	}

}
