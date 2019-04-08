package com.co.ceiba.parqueadero.repositorio.repositorioimpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
				"SELECT ent FROM entrada_parqueo ent WHERE ent.idVehiculo.placa = :placa AND ent.activo = true",
				EntradaParqueo.class);
		query.setParameter("placa", placa);
		return query.getResultList().stream().map(EntradaParqueaderoBuilder::getDTO).collect(Collectors.toList());

	}

	@Override
	public EntradaParqueoDTO guardar(EntradaParqueoDTO entradaParqueoDTO) {

		if (entradaParqueoDTO.getIdVehiculo() instanceof Carro) {
			EntradaParqueo entradaParqueoCarro = new EntradaParqueo();
			Carro carro = (Carro) entradaParqueoDTO.getIdVehiculo();
			entradaParqueoCarro.setActivo(true);
			entradaParqueoCarro.setIdVehiculo(carro);
			entradaParqueoCarro.setFechaEntrada(Timestamp.valueOf(entradaParqueoDTO.getFechaEntrada()));
			entradaParqueoCarro.setTipoVehiculo(carro.getTipoVehiculo());
			entityManager.persist(entradaParqueoCarro);
			return EntradaParqueaderoBuilder.getDTO(entradaParqueoCarro);
		} else if (entradaParqueoDTO.getIdVehiculo() instanceof Moto) {
			EntradaParqueo entradaParqueoMoto = new EntradaParqueo();
			Moto moto = (Moto) entradaParqueoDTO.getIdVehiculo();
			entradaParqueoMoto.setActivo(true);
			entradaParqueoMoto.setIdVehiculo(moto);
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
