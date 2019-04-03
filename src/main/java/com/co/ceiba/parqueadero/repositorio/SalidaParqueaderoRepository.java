package com.co.ceiba.parqueadero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;

public interface SalidaParqueaderoRepository extends JpaRepository<SalidaParqueadero, Long>, JpaSpecificationExecutor<SalidaParqueadero> {
	 
}
