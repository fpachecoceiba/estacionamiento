package com.co.ceiba.parqueadero.dominio.excepcion;

public class ExisteVehiculoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExisteVehiculoException(String mensaje) {
		super(mensaje);
	}

	public ExisteVehiculoException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}

}
