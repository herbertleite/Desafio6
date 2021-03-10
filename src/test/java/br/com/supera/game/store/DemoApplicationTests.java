package br.com.supera.game.store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.supera.game.store.model.User;
import br.com.supera.game.store.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void TesteEndPointBuscaTodosUsuarios() {
		List<User> usuarios = userService.procuraTodos();
		assertEquals(usuarios.size(), 1);
	}

	@Test
	void TesteEndPointBuscaUsuarioPorId() {
		User u = userService.procuraPorId(1L);
		assertEquals(u.getName(), "Joaozinho");
	}

}
