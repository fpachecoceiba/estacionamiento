package com.co.ceiba.parqueadero.repositorio.repositorioImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.builder.CarroBuilder;
import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;



@Transactional
@Repository
public class CarroRepositoryImpl implements CarroRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public VehiculoDTO save(VehiculoDTO vehiculoDTO) {
		Carro carro = CarroBuilder.getCarroEntidad((CarroDTO) vehiculoDTO);
		entityManager.persist(carro);
		return CarroBuilder.getCarroDTO(carro); 
	}

	@Override
	public CarroDTO findById(Long id) {
		Carro carro = entityManager.find(Carro.class, id);
		return CarroBuilder.getCarroDTO(carro);
	}

}
