package com.co.ceiba.parqueadero.builder;


import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.entidad.Tarifa;

public final class TarifaBuilder {
	
	private TarifaBuilder() {
		
		
	}

	public static TarifaDTO getTarifaDTO(Tarifa tarifa) {
		TarifaDTO tarifaDTO = null;
		if (tarifa != null) {
			tarifaDTO = new TarifaDTO(tarifa.getModalidad(), tarifa.getValor(), tarifa.getTipoVehiculo());
		}

		return tarifaDTO;
	}

}
