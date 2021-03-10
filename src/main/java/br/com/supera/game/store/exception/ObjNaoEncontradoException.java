package br.com.supera.game.store.exception;

public class ObjNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjNaoEncontradoException(Object Id) {
		super("Não Encontrado Objeto Id: "+ Id);
	}
	

}
