package com.co.ceiba.parqueadero.repositorio;

import java.util.List;
import java.util.Optional;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;

public interface EntradaParqueoRepository {

	List<EntradaParqueoDTO> listaActivas(String tipoVehiculo);

	List<EntradaParqueoDTO> consultarActivaPorPlaca(String placa);

	EntradaParqueoDTO guardar(EntradaParqueoDTO entradaParqueoDTO);

	void actualizar(EntradaParqueoDTO entradaParqueoDTO);

	List<EntradaParqueoDTO> listarTodas(Boolean activo, Optional<String> tipo, Optional<String> placa);
}
