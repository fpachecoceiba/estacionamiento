package com.co.ceiba.parqueadero.servicio;

import com.co.ceiba.parqueadero.entidad.Tarifa;

public interface TarifaService {
	Tarifa consultarTarifa(String modalidad, String tipoVehiculo);
}
