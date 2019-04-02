package com.co.ceiba.parqueadero.servicio.servicioimpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

@Service
public class EntradaParqueoServiceImpl implements EntradaParqueoService {

	@Autowired
	private EntradaParqueoRepository entradaParqueoRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;
	@Autowired
	private EntradaParqueoService entradaParqueoService;
	private static final Integer STOCK_CARRO = 20;
	private static final Integer STOCK_MOTO = 10;

	@Override
	public EntradaParqueoDTO registrar(EntradaParqueoDTO entradaParqueoDTO) {
		new ValidarStock(entradaParqueoService).validarStock(entradaParqueoDTO.getTipoVehiculo(),
				getStock(entradaParqueoDTO.getTipoVehiculo()));
		Optional<Vehiculo> optVehiculo = vehiculoRepository.findById(entradaParqueoDTO.getIdVehiculo().getIdVehiculo());
		if (optVehiculo.isPresent()) {
			entradaParqueoDTO.setIdVehiculo(optVehiculo.get());
		}
		EntradaParqueo entradaParqueo = getEntidad(entradaParqueoDTO);
		entradaParqueoRepository.save(entradaParqueo);

		return getDTO(entradaParqueo);
	}


	@Override
	public List<EntradaParqueo> listarActivas(String tipo) {
		return entradaParqueoRepository.listaActivas(tipo);
	}

	public static EntradaParqueoDTO getDTO(EntradaParqueo entradaParqueo) {
		return new EntradaParqueoDTO(entradaParqueo.getIdEntrada(), entradaParqueo.getFechaEntrada().toLocalDateTime(),
				entradaParqueo.getActivo(), entradaParqueo.getTipoVehiculo(), entradaParqueo.getIdVehiculo());
	}

	public static EntradaParqueo getEntidad(EntradaParqueoDTO entradaParqueoDTO) {
		return new EntradaParqueo(entradaParqueoDTO.getIdEntrada(),
				Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()), entradaParqueoDTO.getActivo(),
				entradaParqueoDTO.getTipoVehiculo(), entradaParqueoDTO.getIdVehiculo());

	}

	public Integer getStock(String tipoVehiculo) {
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			return STOCK_CARRO;
		} else {
			return STOCK_MOTO;
		}
	}

}
