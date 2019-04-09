package com.co.ceiba.parqueadero.builder;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;

public final class SalidaParqueaderoBuilder {

	private SalidaParqueaderoBuilder() {

	}

	public static SalidaParqueadero getEntidad(SalidaParqueaderoDTO salidaParqueaderoDTO) {
		SalidaParqueadero salidaParqueadero = null;
		if (salidaParqueaderoDTO != null) {
			salidaParqueadero = new SalidaParqueadero();
			salidaParqueadero
					.setEntradaParqueo(EntradaParqueaderoBuilder.getEntidad(salidaParqueaderoDTO.getEntradaParqueo()));
			salidaParqueadero.setFechaSalida(salidaParqueaderoDTO.getFechaSalida());
			salidaParqueadero.setIdSalida(salidaParqueaderoDTO.getIdSalida());
			salidaParqueadero.setValor(salidaParqueaderoDTO.getValor());

		}

		return salidaParqueadero;
	}

	public static SalidaParqueaderoDTO getDTO(SalidaParqueadero salidaParqueadero) {
		SalidaParqueaderoDTO salidaParqueaderoDTO = null;
		if (salidaParqueadero != null) {
			salidaParqueaderoDTO = new SalidaParqueaderoDTO();
			salidaParqueaderoDTO
					.setEntradaParqueo(EntradaParqueaderoBuilder.getDTO(salidaParqueadero.getEntradaParqueo()));
			salidaParqueaderoDTO.setFechaSalida(salidaParqueadero.getFechaSalida());
			salidaParqueaderoDTO.setIdSalida(salidaParqueadero.getIdSalida());
			salidaParqueaderoDTO.setValor(salidaParqueadero.getValor());
		}

		return salidaParqueaderoDTO;
	}
}
