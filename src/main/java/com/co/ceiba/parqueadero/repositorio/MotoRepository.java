package com.co.ceiba.parqueadero.repositorio;

import com.co.ceiba.parqueadero.dominio.MotoDTO;

public interface MotoRepository  {
	MotoDTO findById(Long id);
}
