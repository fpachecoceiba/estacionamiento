package com.co.ceiba.parqueadero.repositorio;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

public interface CarroRepository {
	VehiculoDTO save(VehiculoDTO vehiculoDTO);
	 CarroDTO findById(String  placa);
}
