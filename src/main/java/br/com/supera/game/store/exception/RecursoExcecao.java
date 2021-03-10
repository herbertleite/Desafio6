package br.com.supera.game.store.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecursoExcecao {

	@ExceptionHandler(ObjNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> naoEncontrado(ObjNaoEncontradoException e, HttpServletRequest request) {
		String error = "Não Encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}