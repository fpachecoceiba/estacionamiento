package com.co.ceiba.parqueadero.servicio.reglas;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ExisteVehiculoException;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;

public class ValidarVehiculo {
	public static final String MENSAJE = "El vehiculo ya existe ";

	private VehiculoRepository vehiculoRepository;

	public ValidarVehiculo(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	}

	public void verificar(String placa) {
		VehiculoDTO vehiculoDTO = vehiculoRepository.findByPlaca(placa);
		if (vehiculoDTO != null) {
			throw new ExisteVehiculoException(MENSAJE);
		}
	}

}
