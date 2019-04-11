package com.co.ceiba.parqueadero.repositorio.repositorioimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.co.ceiba.parqueadero.builder.EntradaParqueaderoBuilder;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.EntradaParqueo;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;

@Transactional
@Repository
public class EntradaParqueoRepositoryImpl implements EntradaParqueoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<EntradaParqueoDTO> listaActivas(String tipoVehiculo) {
		TypedQuery<EntradaParqueo> query = entityManager.createQuery(
				"SELECT ent FROM entrada_parqueo ent WHERE ent.tipoVehiculo = :tipoVehiculo AND ent.activo = true ",
				EntradaParqueo.class);
		query.setParameter("tipoVehiculo", tipoVehiculo);
		return query.getResultList().stream().map(EntradaParqueaderoBuilder::getDTO).collect(Collectors.toList());

	} 

	@Override
	public List<EntradaParqueoDTO> consultarActivaPorPlaca(String placa) {
		TypedQuery<EntradaParqueo> query = entityManager.createQuery(
				"SELECT ent FROM entrada_parqueo ent WHERE ent.vehiculo.placa = :placa AND ent.activo = true",
				EntradaParqueo.class);
		query.setParameter("placa", placa);
		return query.getResultList().stream().map(EntradaParqueaderoBuilder::getDTO).collect(Collectors.toList());

	}

	@Override
	public List<EntradaParqueoDTO> listarTodas(Boolean activo,Optional<String> tipo, Optional<String> placa) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EntradaParqueo> criteria = builder.createQuery(EntradaParqueo.class);
		Root<EntradaParqueo> entityRoot = criteria.from(EntradaParqueo.class);

		List<Predicate> filtros = new ArrayList<>();
		filtros.add(builder.equal(entityRoot.get("activo"), activo));

		if (tipo.isPresent()) {
			filtros.add(builder.equal(entityRoot.get("tipoVehiculo"), tipo.get()));
		}

		if (placa.isPresent()) {
			filtros.add(builder.equal(entityRoot.get("vehiculo").get("placa"), placa.get()));
		}

		if (!filtros.isEmpty()) {
			criteria.where(filtros.toArray(new Predicate[filtros.size()]));
		}

		return entityManager.createQuery(criteria).getResultStream().map(EntradaParqueaderoBuilder::getDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EntradaParqueoDTO guardar(EntradaParqueoDTO entradaParqueoDTO) {

		if (entradaParqueoDTO.getVehiculo() instanceof Carro) {
			EntradaParqueo entradaParqueoCarro = new EntradaParqueo();
			Carro carro = (Carro) entradaParqueoDTO.getVehiculo();
			entradaParqueoCarro.setActivo(true);
			entradaParqueoCarro.setPlaca(carro);
			entradaParqueoCarro.setFechaEntrada(Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()));
			entradaParqueoCarro.setTipoVehiculo(carro.getTipoVehiculo());
			entityManager.persist(entradaParqueoCarro);
			return EntradaParqueaderoBuilder.getDTO(entradaParqueoCarro);
		} else if (entradaParqueoDTO.getVehiculo() instanceof Moto) {
			EntradaParqueo entradaParqueoMoto = new EntradaParqueo();
			Moto moto = (Moto) entradaParqueoDTO.getVehiculo();
			entradaParqueoMoto.setActivo(true);
			entradaParqueoMoto.setPlaca(moto);
			entradaParqueoMoto.setFechaEntrada(Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()));
			entradaParqueoMoto.setTipoVehiculo(moto.getTipoVehiculo());
			entityManager.persist(entradaParqueoMoto);
			return EntradaParqueaderoBuilder.getDTO(entradaParqueoMoto);
		}

		return null;
	}

	@Override
	public void actualizar(EntradaParqueoDTO entradaParqueoDTO) {
		entityManager.merge(EntradaParqueaderoBuilder.getEntidad(entradaParqueoDTO));
	}

}
