package com.co.ceiba.parqueadero.repositorio.repositorioImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;

import builder.MotoBuilder;
@Transactional
@Repository
public class MotoRepositoryImpl implements MotoRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MotoDTO findById(Long id) {
		Moto moto = entityManager.find(Moto.class, id);
		return MotoBuilder.getMotoDTO(moto);
	}
	


}
