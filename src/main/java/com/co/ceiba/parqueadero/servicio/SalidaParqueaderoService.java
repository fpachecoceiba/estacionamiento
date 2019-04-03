package com.co.ceiba.parqueadero.servicio;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

public interface SalidaParqueaderoService {
	
	SalidaParqueaderoDTO guardar(VehiculoDTO vehiculoDTO);

}
