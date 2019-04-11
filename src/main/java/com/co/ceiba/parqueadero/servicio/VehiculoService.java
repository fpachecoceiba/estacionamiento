package com.co.ceiba.parqueadero.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

@Service
public class VehiculoService {
	
	private VehiculoRepository vehiculoRepository;
	private EntradaParqueoRepository entradaParqueoRepository;
	private LocalDateTime fechaEntrada = LocalDateTime.now();


	public VehiculoService(VehiculoRepository vehiculoRepository, EntradaParqueoRepository entradaParqueoRepository) {
		this.vehiculoRepository = vehiculoRepository;
		this.entradaParqueoRepository = entradaParqueoRepository;
	} 
 
	public VehiculoDTO guardar(VehiculoDTO vehiculoDTO) { 
		if (!new ValidarEntradaParqueadero(entradaParqueoRepository).ingresoValidoSegunDiaPlaca(vehiculoDTO.getPlaca(), fechaEntrada)) {
			throw new ParqueaderoNoDisponibleException(ValidarEntradaParqueadero.MENSAJE);
		}
		
		new ValidarVehiculo(vehiculoRepository).verificar(vehiculoDTO.getPlaca());

		if (vehiculoDTO instanceof CarroDTO) {
			CarroDTO carroDTO = (CarroDTO) vehiculoDTO; 
			carroDTO.setTipoVehiculo(TipoVehiculo.CARRO.toString());
			return vehiculoRepository.registrar(carroDTO);
		}
		if (vehiculoDTO instanceof MotoDTO) { 
			MotoDTO motoDTO = (MotoDTO) vehiculoDTO;
			motoDTO.setTipoVehiculo(TipoVehiculo.MOTO.toString());
			return vehiculoRepository.registrar(motoDTO);
		}
		
		return null;
 
	}

	public List<VehiculoDTO> listarPorTipo(String tipoVehiculo) {
		return vehiculoRepository.listarPorTipo(tipoVehiculo);
	}

}
