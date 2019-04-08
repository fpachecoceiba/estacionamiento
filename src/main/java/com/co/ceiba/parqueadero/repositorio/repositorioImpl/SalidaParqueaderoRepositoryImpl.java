package com.co.ceiba.parqueadero.repositorio.repositorioImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;
import com.co.ceiba.parqueadero.repositorio.SalidaParqueaderoRepository;

import builder.SalidaParqueaderoBuilder;

@Transactional
@Repository
public class SalidaParqueaderoRepositoryImpl implements SalidaParqueaderoRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SalidaParqueaderoDTO registrar(SalidaParqueaderoDTO salidaParqueaderoDTO) {

		SalidaParqueadero salidaParqueadero = SalidaParqueaderoBuilder
				.getSalidaParqueaderoEntidad(salidaParqueaderoDTO);
		entityManager.persist(salidaParqueadero);
		entityManager.merge(salidaParqueadero.getEntradaParqueo());
		return SalidaParqueaderoBuilder.getSalidaParqueaderoDTO(salidaParqueadero);

	}

}