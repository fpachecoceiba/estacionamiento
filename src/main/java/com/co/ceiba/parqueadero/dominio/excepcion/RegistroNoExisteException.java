package com.co.ceiba.parqueadero.dominio.excepcion;

public class RegistroNoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistroNoExisteException(String mensaje) {
		super(mensaje);
	}

	public RegistroNoExisteException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}
}
