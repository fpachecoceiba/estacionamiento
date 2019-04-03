package com.co.ceiba.parqueadero.servicio.reglas;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;

public class ValidarStock {


	public static final String MENSAJE_CARRO = "No hay disponibilidad para carros en el parqueadero";
	public static final String MENSAJE_MOTO = "No hay disponibilidad para motos en el parqueadero";

	private EntradaParqueoService entradaParqueoService;

	public ValidarStock(EntradaParqueoService entradaParqueoService) {
		this.entradaParqueoService = entradaParqueoService;
	}   

	public void validarStock(String tipoVehiculo,Integer stock) { 
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			List<EntradaParqueoDTO> cantCarroActiva = entradaParqueoService.listarActivas(tipoVehiculo);
			if (cantCarroActiva.size() >= stock) {
				throw new ParqueaderoNoDisponibleException(MENSAJE_CARRO);
			}
		} else if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			List<EntradaParqueoDTO> cantMotoActiva = entradaParqueoService.listarActivas(tipoVehiculo);
			if (cantMotoActiva.size() >= stock) {
				throw new ParqueaderoNoDisponibleException(MENSAJE_MOTO); 
			} 

		}

	}
}
