package com.co.ceiba.parqueadero.repositorio.repositorioImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;

import builder.CarroBuilder;
import builder.MotoBuilder;
import builder.VehiculoBuilder;

@Transactional
@Repository
public class VehiculoRepositoryImpl implements VehiculoRepository {
	@PersistenceContext
	private EntityManager entityManager;
	 
	@Override
	public VehiculoDTO registrar(VehiculoDTO vehiculoDTO) {
		if (vehiculoDTO instanceof CarroDTO) {
			CarroDTO carroDTO = (CarroDTO) vehiculoDTO;
			Carro carro = CarroBuilder.getCarroEntidad(carroDTO);
			entityManager.persist(carro);
			return CarroBuilder.getCarroDTO(carro); 

		}
		if (vehiculoDTO instanceof MotoDTO) {
			MotoDTO motoDTO = (MotoDTO) vehiculoDTO;
			Moto moto = MotoBuilder.getEntidad(motoDTO);
			entityManager.persist(moto);
			return MotoBuilder.getMotoDTO(moto);
		}

		return null;
	}

	@Override
	public List<VehiculoDTO> findByPlaca(String placa) {
		TypedQuery<Vehiculo> query = entityManager.createQuery("SELECT vh FROM vehiculo vh WHERE vh.placa = :placa ",
				Vehiculo.class);
		query.setParameter("placa", placa);
		return query.getResultList().stream().map(VehiculoBuilder::getVehiculoDTO).collect(Collectors.toList());
	}

	@Override
	public List<VehiculoDTO> listarPorTipo(String tipoVehiculo) {
		TypedQuery<Vehiculo> query = entityManager
				.createQuery("SELECT vh FROM vehiculo vh WHERE vh.tipoVehiculo = :tipoVehiculo ", Vehiculo.class);
		query.setParameter("tipoVehiculo", tipoVehiculo);
		return query.getResultList().stream().map(VehiculoBuilder::getVehiculoDTO).collect(Collectors.toList());
	}

	@Override
	public VehiculoDTO findById(Long id) {
		Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
		if (vehiculo != null) {
			return VehiculoBuilder.getVehiculoDTO(vehiculo);
		}
		return null;
	}

}
