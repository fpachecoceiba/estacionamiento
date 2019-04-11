package com.co.ceiba.parqueadero.repositorio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;

public interface SalidaParqueaderoRepository {
	SalidaParqueaderoDTO registrar(SalidaParqueaderoDTO salidaParqueaderoDTO);

	List<SalidaParqueaderoDTO> consultar(String placa);

}
