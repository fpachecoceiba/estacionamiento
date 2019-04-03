package com.co.ceiba.parqueadero.servicio.servicioimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.entidad.Tarifa;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;
import com.co.ceiba.parqueadero.servicio.TarifaService;

@Service
public class TarifaServiceImpl implements TarifaService {
	@Autowired
	private TarifaRepository tarifaRepository;

	@Override
	public TarifaDTO consultarTarifa(String modalidad, String tipoVehiculo) {

		return getDTO(tarifaRepository.consultarTarifa(modalidad, tipoVehiculo));
	}

	public static Tarifa getEntidad(TarifaDTO tarifaDTO) {
		return new Tarifa(tarifaDTO.getModalidad(), tarifaDTO.getValor(), tarifaDTO.getTipoVehiculo());
	}

	public static TarifaDTO getDTO(Tarifa tarifa) {
		return new TarifaDTO(tarifa.getModalidad(), tarifa.getValor(), tarifa.getTipoVehiculo());
	}

}
