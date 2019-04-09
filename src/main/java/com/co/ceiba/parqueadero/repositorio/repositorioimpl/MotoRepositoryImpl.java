package com.co.ceiba.parqueadero.repositorio.repositorioimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.builder.MotoBuilder;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;

@Transactional
@Repository
public class MotoRepositoryImpl implements MotoRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MotoDTO findByPlaca(String placa) {
		Moto moto = entityManager.find(Moto.class, placa);
		return MotoBuilder.getDTO(moto);
	}
	


}
