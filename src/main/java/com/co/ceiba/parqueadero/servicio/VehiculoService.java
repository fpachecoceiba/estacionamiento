package com.co.ceiba.parqueadero.servicio;

import java.util.List;

public interface VehiculoService {
	
	Object guardar(Object vehiculoDTO);

	Object consultar(Long idVehiculo);

	List<Object> listar();

	Integer eliminar(Long idVehiculo);

}
