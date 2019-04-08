package com.co.ceiba.parqueadero.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

@Service
public class VehiculoService {
	
	private VehiculoRepository vehiculoRepository;

	public VehiculoService(VehiculoRepository vehiculoRepository) {
		this.vehiculoRepository = vehiculoRepository;
	} 

	public VehiculoDTO guardar(VehiculoDTO vehiculoDTO) { 
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
