package com.co.ceiba.parqueadero.servicio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

public interface EntradaParqueoService {
	EntradaParqueoDTO registrar(VehiculoDTO vehiculo);
	List<EntradaParqueoDTO> listarActivas(String tipo);
	EntradaParqueoDTO consultarActivaPorId(String placa);
}
