package com.co.ceiba.parqueadero.repositorio;

import java.util.List;

import com.co.ceiba.parqueadero.dominio.TarifaDTO;

public interface TarifaRepository  {

	List<TarifaDTO> consultarTarifa(String modalidad,String tipoVehiculo);

}
