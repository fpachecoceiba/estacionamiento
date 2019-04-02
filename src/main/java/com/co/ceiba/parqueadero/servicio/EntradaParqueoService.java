package com.co.ceiba.parqueadero.servicio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;

public interface EntradaParqueoService {
	EntradaParqueoDTO registrar(VehiculoDTO vehiculo);
	List<EntradaParqueo> listarActivas(String tipo);
	EntradaParqueo consultarActivaPorId(String placa);
}
