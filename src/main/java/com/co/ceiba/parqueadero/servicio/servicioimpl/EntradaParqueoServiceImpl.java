package com.co.ceiba.parqueadero.servicio.servicioimpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

@Service
public class EntradaParqueoServiceImpl implements EntradaParqueoService {

	@Autowired
	EntradaParqueoRepository entradaParqueoRepository;
	@Autowired
	ValidarStock validarStock;

	@Override
	public EntradaParqueoDTO registrar(EntradaParqueoDTO entradaParqueoDTO) {
		Boolean valida = validarStock.validarStock(entradaParqueoDTO.getTipoVehiculo());
		EntradaParqueo entradaParqueo = getEntidad(entradaParqueoDTO);
		if (valida) {
			System.out.print("validando-->" + valida);
			entradaParqueoRepository.save(entradaParqueo);
		} else {
			System.out.print("parqueadero lleno...!");
		}
		return getDTO(entradaParqueo);
	}

	@Override
	public List<EntradaParqueo> listarActivas(String tipo) {
		return entradaParqueoRepository.listaActivas(tipo);
	}

	public static EntradaParqueoDTO getDTO(EntradaParqueo entradaParqueo) {
		return new EntradaParqueoDTO(entradaParqueo.getIdEntrada(), entradaParqueo.getFechaEntrada().toLocalDateTime(),
				entradaParqueo.getActivo(), entradaParqueo.getTipoVehiculo());
	}

	public static EntradaParqueo getEntidad(EntradaParqueoDTO entradaParqueoDTO) {
		return new EntradaParqueo(entradaParqueoDTO.getIdEntrada(),
				Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()), entradaParqueoDTO.getActivo(),
				entradaParqueoDTO.getTipoVehiculo());

	}

}
