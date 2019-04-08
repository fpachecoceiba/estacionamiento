package com.co.ceiba.parqueadero.repositorio.repositorioImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.builder.TarifaBuilder;
import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.entidad.Tarifa;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;


@Transactional
@Repository
public class TarifaRepositoryImpl implements TarifaRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TarifaDTO> consultarTarifa(String modalidad, String tipoVehiculo) {

		TypedQuery<Tarifa> query = entityManager.createQuery(
				"SELECT tr FROM tarifa tr WHERE tr.modalidad = :modalidad AND tr.tipoVehiculo = :tipoVehiculo",
				Tarifa.class);
		query.setParameter("modalidad", modalidad).setParameter("tipoVehiculo", tipoVehiculo);
	

		return query.getResultList().stream().map(TarifaBuilder::getTarifaDTO).collect(Collectors.toList());

	}

}
