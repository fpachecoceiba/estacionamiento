package com.co.ceiba.parqueadero.repositorio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

public interface VehiculoRepository {

	 VehiculoDTO registrar(VehiculoDTO vehiculoDTO);

	 List<VehiculoDTO> findByPlaca(String placa);

	 List<VehiculoDTO> listarPorTipo(String tipoVehiculo);
	
	 VehiculoDTO findById(Long id);

}
