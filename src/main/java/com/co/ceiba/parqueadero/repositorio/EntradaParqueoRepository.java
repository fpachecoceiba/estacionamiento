package com.co.ceiba.parqueadero.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.ceiba.parqueadero.entidad.EntradaParqueo;

public interface EntradaParqueoRepository
		extends JpaRepository<EntradaParqueo, Long>, JpaSpecificationExecutor<EntradaParqueo> {
	
	  @Query(value = "SELECT ent FROM entrada_parqueo ent WHERE ent.tipoVehiculo = :tipoVehiculo AND ent.activo = true")
	  List<EntradaParqueo> listaActivas(@Param(value = "tipoVehiculo")  String tipoVehiculo);
}
