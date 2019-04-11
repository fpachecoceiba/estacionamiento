package com.co.ceiba.parqueadero.repositorio.repositorioimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.builder.SalidaParqueaderoBuilder;
import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;
import com.co.ceiba.parqueadero.repositorio.SalidaParqueaderoRepository;


@Transactional
@Repository
public class SalidaParqueaderoRepositoryImpl implements SalidaParqueaderoRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SalidaParqueaderoDTO registrar(SalidaParqueaderoDTO salidaParqueaderoDTO) {

		SalidaParqueadero salidaParqueadero = SalidaParqueaderoBuilder
				.getEntidad(salidaParqueaderoDTO);
		entityManager.persist(salidaParqueadero);
		entityManager.merge(salidaParqueadero.getEntradaParqueo());
		return SalidaParqueaderoBuilder.getDTO(salidaParqueadero);

	}

	@Override
	public List<SalidaParqueaderoDTO> consultar(String placa) {
		TypedQuery<SalidaParqueadero> query = entityManager.createQuery(
				"SELECT sl FROM salida_parqueadero  sl WHERE sl.entradaParqueo.vehiculo.placa = :placa ",
				SalidaParqueadero.class);
		query.setParameter("placa", placa);
		return query.getResultList().stream().map(SalidaParqueaderoBuilder::getDTO).collect(Collectors.toList());
		
	}

}
