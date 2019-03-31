package com.co.ceiba.parqueadero.servicio.servicioImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	@Autowired
	private CarroRepository carroRepository;

	@Override
	public Object guardar(Object vehiculoObject) {
		if (vehiculoObject instanceof CarroDTO) {
			CarroDTO carroDTO = (CarroDTO) vehiculoObject;
			Carro carro = getCarroEntidad(carroDTO);
			carroRepository.save(carro);
			return getCarroDTO(carro);
		}
		return null;
	}

	@Override
	public Object consultar(Long idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer eliminar(Long idVehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	public static CarroDTO getCarroDTO(Carro carro) {
		return new CarroDTO(carro.getIdCarro(), carro.getPlaca(), carro.getModelo());
	}

	public static Carro getCarroEntidad(CarroDTO carroDTO) {
		return new Carro(carroDTO.getIdCarro(), carroDTO.getPlaca(), carroDTO.getModelo());
	}

}
