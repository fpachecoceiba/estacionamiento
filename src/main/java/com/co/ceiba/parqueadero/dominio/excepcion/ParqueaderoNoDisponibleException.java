package com.co.ceiba.parqueadero.dominio.excepcion;

public class ParqueaderoNoDisponibleException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;

	public ParqueaderoNoDisponibleException(String mensaje) {
		super(mensaje);
	}

	public ParqueaderoNoDisponibleException(String mensaje, Throwable throwable) {
		super(mensaje, throwable);
	}

}
