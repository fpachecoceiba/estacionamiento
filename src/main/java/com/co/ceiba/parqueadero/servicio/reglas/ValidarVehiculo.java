package com.co.ceiba.parqueadero.servicio.reglas;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ExisteVehiculoException;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

public class ValidarVehiculo {
	public static final String MENSAJE = "El vehiculo ya existe ";
	private VehiculoService vehiculoService;
	public ValidarVehiculo(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}
	public void verificar(String placa) {
		VehiculoDTO propietatio = vehiculoService.buscarPorPlaca(placa);
		if (propietatio != null) {
			throw new ExisteVehiculoException(MENSAJE);
		}
	}
	
}
