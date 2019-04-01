package com.co.ceiba.parqueadero.servicio.servicioimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.VehiculoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

@Service
public class VehiculoServiceImpl implements VehiculoService {
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;
	@Autowired
	private MotoRepository motoRepository;

	@Autowired
	private VehiculoService vehiculoService;

	@Override
	public VehiculoDTO guardar(VehiculoDTO vehiculoDTO) {
		new ValidarVehiculo(vehiculoService).verificar(vehiculoDTO.getPlaca());
		if (vehiculoDTO instanceof CarroDTO) {
			Vehiculo vehiculo = getVehiculoEntidad(vehiculoDTO);
			vehiculo.setTipoVehiculo(TipoVehiculo.CARRO.toString());
			vehiculoRepository.save(vehiculo);
			Carro carro = getCarroEntidad((CarroDTO) vehiculoDTO);
			carro.setIdVehiculo(vehiculo);
			carroRepository.save(carro);
			return getCarroDTO(carro);
		}

		if (vehiculoDTO instanceof MotoDTO) {
			Vehiculo vehiculo = getVehiculoEntidad(vehiculoDTO);
			vehiculo.setTipoVehiculo(TipoVehiculo.MOTO.toString());
			vehiculoRepository.save(vehiculo);
			Moto moto = getMotoEntidad((MotoDTO) vehiculoDTO);
			moto.setIdVehiculo(vehiculo);
			motoRepository.save(moto);
			return getMotoDTO(moto);
		}

		return null;
	}

	@Override
	public VehiculoDTO consultar(Long idVehiculo) {
		return null;
	}

	@Override
	public List<VehiculoDTO> listar() {
		return null;
	}

	@Override
	public Integer eliminar(Long idVehiculo) {
		return null;
	}

	@Override
	public VehiculoDTO buscarPorPlaca(String placa) {
		Vehiculo vehiculo = this.vehiculoRepository.findByPlaca(placa);
		if (vehiculo != null) {
			return getVehiculoDTO(vehiculo);
		} else {
			return null;
		}
	}

	public static CarroDTO getCarroDTO(Carro carro) {
		return new CarroDTO(carro.getIdCarro(), carro.getModelo(), carro.getIdVehiculo().getIdVehiculo(),
				carro.getIdVehiculo().getPlaca(), TipoVehiculo.CARRO.toString());
	}

	public static Carro getCarroEntidad(CarroDTO carroDTO) {
		return new Carro(carroDTO.getIdCarro(), carroDTO.getModelo());
	}

	public static MotoDTO getMotoDTO(Moto moto) {
		return new MotoDTO(moto.getIdMoto(), moto.getCilindraje(), moto.getIdVehiculo().getIdVehiculo(),
				moto.getIdVehiculo().getPlaca(), TipoVehiculo.MOTO.toString());
	}

	public static Moto getMotoEntidad(MotoDTO motoDTO) {
		return new Moto(motoDTO.getIdMoto(), motoDTO.getCilindraje());
	}

	public static Vehiculo getVehiculoEntidad(VehiculoDTO vehiculoDTO) {
		return new Vehiculo(vehiculoDTO.getIdVehiculo(), vehiculoDTO.getPlaca());
	}

	public static VehiculoDTO getVehiculoDTO(Vehiculo vehiculo) {
		return new VehiculoDTO(vehiculo.getIdVehiculo(), vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
	}

}
