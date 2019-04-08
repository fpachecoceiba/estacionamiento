package com.co.ceiba.parqueadero.servicio.reglas;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;

public class ValidarStock {

	public static final String MENSAJE_CARRO = "No hay disponibilidad para carros en el parqueadero";
	public static final String MENSAJE_MOTO = "No hay disponibilidad para motos en el parqueadero";

	private EntradaParqueoRepository entradaParqueoRepository;

	public ValidarStock(EntradaParqueoRepository entradaParqueoRepository) {
		this.entradaParqueoRepository = entradaParqueoRepository;
	}

	public Boolean validarStock(String tipoVehiculo, Integer stock) {
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			List<EntradaParqueoDTO> cantCarroActiva = entradaParqueoRepository.listaActivas(tipoVehiculo);
			if (cantCarroActiva.size() >= stock) {
				return false;
				
			}
		} else if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			List<EntradaParqueoDTO> cantMotoActiva = entradaParqueoRepository.listaActivas(tipoVehiculo);
			if (cantMotoActiva.size() >= stock) {
				return false;
				
			}

		}
		return true;

	}

}
