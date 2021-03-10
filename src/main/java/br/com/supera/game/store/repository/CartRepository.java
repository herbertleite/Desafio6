package br.com.supera.game.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.supera.game.store.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
