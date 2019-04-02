package com.co.ceiba.parqueadero.servicio.servicioimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.entidad.Tarifa;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;
import com.co.ceiba.parqueadero.servicio.TarifaService;

@Service
public class TarifaServiceImpl implements TarifaService {
	@Autowired
	private TarifaRepository tarifaRepository;

	@Override
	public Tarifa consultarTarifa(String modalidad, String tipoVehiculo) {

		return tarifaRepository.consultarTarifa(modalidad, tipoVehiculo);
	}

}
