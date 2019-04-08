package com.co.ceiba.parqueadero.servicio;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

import builder.CarroBuilder;
import builder.MotoBuilder;

@Service
public class EntradaParqueoService {

	private static final Integer STOCK_CARRO = 20;
	private static final Integer STOCK_MOTO = 10;
	private static final String MENSAJE_NOEXISTE_VEHICULO = "No existe un vehiculo registrado para esta informacion";

	private LocalDateTime fechaEntrada = LocalDateTime.now();

	private EntradaParqueoRepository entradaParqueoRepository; 
	private CarroRepository carroRepository;
	private MotoRepository motoRepository;
	private VehiculoRepository vehiculoRepository;

	public EntradaParqueoService(EntradaParqueoRepository entradaParqueoRepository,
			VehiculoRepository vehiculoRepository, CarroRepository carroRepository, MotoRepository motoRepository) {
		this.entradaParqueoRepository = entradaParqueoRepository;
		this.carroRepository = carroRepository;
		this.motoRepository = motoRepository;
		this.vehiculoRepository = vehiculoRepository;
	}

	public EntradaParqueoDTO registrar(VehiculoDTO vehiculoDTO) {

		VehiculoDTO vehiculoDTO2 = vehiculoRepository.findById(vehiculoDTO.getIdVehiculo());
		if (vehiculoDTO2 == null) {
			throw new ParqueaderoNoDisponibleException(MENSAJE_NOEXISTE_VEHICULO);
		}

		String tipoVehiculo = vehiculoDTO2.getTipoVehiculo();
		String placa = vehiculoDTO2.getPlaca();
		Long idVehiculo = vehiculoDTO2.getIdVehiculo();

		if (!new ValidarEntradaParqueadero(entradaParqueoRepository).ingresoValidoSegunDiaPlaca(placa, fechaEntrada)) {
			throw new ParqueaderoNoDisponibleException(ValidarEntradaParqueadero.MENSAJE);
		}
 
		if (new ValidarEntradaParqueadero(entradaParqueoRepository).existeEntradaRegistrada(placa)) {
			throw new ParqueaderoNoDisponibleException(ValidarEntradaParqueadero.MENSAJE_EXISTE);
		}
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			if (!new ValidarStock(entradaParqueoRepository).validarStock(tipoVehiculo, getStock(tipoVehiculo))) {
				throw new ParqueaderoNoDisponibleException(ValidarStock.MENSAJE_CARRO);
			} 
			CarroDTO carroDTO = carroRepository.findById(idVehiculo);
			EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO();
			entradaParqueoDTO.setActivo(Boolean.TRUE);
			entradaParqueoDTO.setFechaEntrada(fechaEntrada);
			entradaParqueoDTO.setIdVehiculo(CarroBuilder.getCarroEntidad(carroDTO));
			return entradaParqueoRepository.guardar(entradaParqueoDTO);

		} else if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			if (!new ValidarStock(entradaParqueoRepository).validarStock(tipoVehiculo, getStock(tipoVehiculo))) {
				throw new ParqueaderoNoDisponibleException(ValidarStock.MENSAJE_MOTO);
			}
			MotoDTO motoDTO = motoRepository.findById(idVehiculo);
			EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO();
			entradaParqueoDTO.setActivo(Boolean.TRUE);
			entradaParqueoDTO.setFechaEntrada(fechaEntrada);
			entradaParqueoDTO.setIdVehiculo(MotoBuilder.getEntidad(motoDTO));
			return entradaParqueoRepository.guardar(entradaParqueoDTO);
		}

		return null;

	}

	public Integer getStock(String tipoVehiculo) {
		if (tipoVehiculo.equals(TipoVehiculo.CARRO.toString())) {
			return STOCK_CARRO;
		} else {
			return STOCK_MOTO;
		}
	}

}
