package com.co.ceiba.parqueadero.servicio.servicioimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

@Service
public class EntradaParqueoServiceImpl implements EntradaParqueoService {

	private static final Integer STOCK_CARRO = 20;
	private static final Integer STOCK_MOTO = 10;
	private LocalDateTime fechaEntrada = LocalDateTime.now();

	@Autowired
	private EntradaParqueoRepository entradaParqueoRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Override
	public EntradaParqueoDTO registrar(VehiculoDTO vehiculoDTO) {
			Optional<Vehiculo> optVehiculo = vehiculoRepository.findById(vehiculoDTO.getIdVehiculo());
			if (optVehiculo.isPresent()) {
				String tipoVehiculo = optVehiculo.get().getTipoVehiculo();
				String placa = optVehiculo.get().getPlaca();
				new ValidarStock(this).validarStock(tipoVehiculo, getStock(optVehiculo.get().getTipoVehiculo()));

				if (!new ValidarEntradaParqueadero(this).ingresoValidoSegunDiaPlaca(placa, fechaEntrada)) {
					throw new ParqueaderoNoDisponibleException(ValidarEntradaParqueadero.MENSAJE);
				}

				if (new ValidarEntradaParqueadero(this).existeEntradaRegistrada(placa)) {
					throw new ParqueaderoNoDisponibleException(ValidarEntradaParqueadero.MENSAJE_EXISTE);
				}

				EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO();
				entradaParqueoDTO.setActivo(true);
				entradaParqueoDTO.setFechaEntrada(fechaEntrada);
				entradaParqueoDTO.setIdVehiculo(optVehiculo.get());
				EntradaParqueo entradaParqueo = getEntidad(entradaParqueoDTO);
				entradaParqueoRepository.save(entradaParqueo);
				return getDTO(entradaParqueo);
			}
		return null;
	}

	@Override
	public List<EntradaParqueoDTO> listarActivas(String tipo) {
		return entradaParqueoRepository.listaActivas(tipo).stream().map(EntradaParqueoServiceImpl::getDTO)
				.collect(Collectors.toList());

	}

	public static EntradaParqueoDTO getDTO(EntradaParqueo entradaParqueo) {
		return new EntradaParqueoDTO(entradaParqueo.getIdEntrada(), entradaParqueo.getFechaEntrada().toLocalDateTime(),
				entradaParqueo.getActivo(), entradaParqueo.getIdVehiculo());
	}

	public static EntradaParqueo getEntidad(EntradaParqueoDTO entradaParqueoDTO) {
		return new EntradaParqueo(entradaParqueoDTO.getIdEntrada(),
				Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()), entradaParqueoDTO.getActivo(),
				entradaParqueoDTO.getIdVehiculo().getTipoVehiculo(), entradaParqueoDTO.getIdVehiculo());

	}

	public Integer getStock(String tipoVehiculo) {
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			return STOCK_CARRO;
		} else {
			return STOCK_MOTO;
		}
	}

	@Override
	public EntradaParqueoDTO consultarActivaPorId(String placa) {
		EntradaParqueo entradaParqueo = this.entradaParqueoRepository.consultarActivaPorId(placa);
		if (entradaParqueo != null) {
			return getDTO(entradaParqueo);
		} else {
			return null;
		}

	}

}
