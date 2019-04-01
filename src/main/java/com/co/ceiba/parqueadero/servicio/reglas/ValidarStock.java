package com.co.ceiba.parqueadero.servicio.reglas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
@Service
public class ValidarStock {
	private static Integer stockCarro = 20;
	private static Integer stockMoto = 10;

	@Autowired
	private EntradaParqueoService entradaParqueoService;

	public Boolean validarStock(String tipoVehiculo) {
		Boolean disponible = false;
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {

			List<EntradaParqueo> cantCarroActiva = entradaParqueoService.listarActivas(tipoVehiculo);
			if (cantCarroActiva.size() < stockCarro) {
				disponible = true;
			}
		} else if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			List<EntradaParqueo> cantMotoActiva = entradaParqueoService.listarActivas(tipoVehiculo);
			if (cantMotoActiva.size() < stockMoto) {
				disponible = true;
			}

		}
		return disponible;

	}
}
