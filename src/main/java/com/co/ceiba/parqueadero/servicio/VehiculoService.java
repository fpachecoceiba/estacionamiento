package com.co.ceiba.parqueadero.servicio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

public interface VehiculoService {
	
	VehiculoDTO guardar(VehiculoDTO vehiculoDTO);

	VehiculoDTO consultar(Long idVehiculo);

	List<VehiculoDTO> listar();

	Integer eliminar(Long idVehiculo);

	VehiculoDTO buscarPorPlaca(String placa);

}
