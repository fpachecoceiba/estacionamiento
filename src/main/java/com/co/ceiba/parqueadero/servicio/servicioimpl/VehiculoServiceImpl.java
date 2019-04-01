package com.co.ceiba.parqueadero.servicio.servicioimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private MotoRepository motoRepository;

	@Override
	public Object guardar(Object vehiculoObject) {
		if (vehiculoObject instanceof CarroDTO) {
			CarroDTO carroDTO = (CarroDTO) vehiculoObject;
			Carro carro = getCarroEntidad(carroDTO);
			carroRepository.save(carro);
			return getCarroDTO(carro);
		} else if (vehiculoObject instanceof MotoDTO) {
			MotoDTO motoDTO = (MotoDTO) vehiculoObject;
			Moto moto = getMotoEntidad(motoDTO);
			motoRepository.save(moto);
			return getMotoDTO(moto);
		}
		return null;
	}

	@Override
	public Object consultar(Long idVehiculo) {
		return null;
	}

	@Override
	public List<Object> listar() {
		return null;
	}

	@Override
	public Integer eliminar(Long idVehiculo) {
		return null;
	}

	public static CarroDTO getCarroDTO(Carro carro) {
		return new CarroDTO(carro.getIdCarro(), carro.getPlaca(), carro.getModelo());
	}

	public static Carro getCarroEntidad(CarroDTO carroDTO) {
		return new Carro(carroDTO.getIdCarro(), carroDTO.getPlaca(), carroDTO.getModelo());
	}

	public static MotoDTO getMotoDTO(Moto moto) {
		return new MotoDTO(moto.getIdMoto(), moto.getPlaca(), moto.getCilindraje());
	}

	public static Moto getMotoEntidad(MotoDTO motoDTO) {
		return new Moto(motoDTO.getIdMoto(), motoDTO.getPlaca(), motoDTO.getCilindraje());
	}

}
