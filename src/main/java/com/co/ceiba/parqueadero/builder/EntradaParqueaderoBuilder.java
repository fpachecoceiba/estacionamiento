package com.co.ceiba.parqueadero.builder;



import java.sql.Timestamp;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;

public final class EntradaParqueaderoBuilder {
	
	private EntradaParqueaderoBuilder() {
		
	}
	public static EntradaParqueoDTO getDTO(EntradaParqueo entradaParqueo) {
		return new EntradaParqueoDTO(entradaParqueo.getIdEntrada(), entradaParqueo.getFechaEntrada().toLocalDateTime(),
				entradaParqueo.getActivo(), entradaParqueo.getIdVehiculo());
	}

	public static EntradaParqueo getEntidad(EntradaParqueoDTO entradaParqueoDTO) {
		return new EntradaParqueo(entradaParqueoDTO.getIdEntrada(),
				Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()), entradaParqueoDTO.getActivo(),
				entradaParqueoDTO.getIdVehiculo().getTipoVehiculo(), entradaParqueoDTO.getIdVehiculo());

	}

}
