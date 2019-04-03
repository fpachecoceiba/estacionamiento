package com.co.ceiba.parqueadero.servicio;

import com.co.ceiba.parqueadero.dominio.TarifaDTO;

public interface TarifaService {
	TarifaDTO consultarTarifa(String modalidad, String tipoVehiculo);
}
