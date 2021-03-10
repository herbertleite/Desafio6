package br.com.supera.game.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.supera.game.store.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
