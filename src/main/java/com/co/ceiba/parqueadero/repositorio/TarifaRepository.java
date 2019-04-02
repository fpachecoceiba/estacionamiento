package com.co.ceiba.parqueadero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.parqueadero.entidad.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Long>, JpaSpecificationExecutor<Tarifa> {

	@Query(value = "SELECT tr FROM tarifa tr WHERE tr.modalidad = :modalidad AND tr.tipoVehiculo = :tipoVehiculo")
	Tarifa consultarTarifa(@Param(value = "modalidad") String modalidad,
			@Param(value = "tipoVehiculo") String tipoVehiculo);

}
