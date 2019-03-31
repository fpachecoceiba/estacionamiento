package com.co.ceiba.parqueadero.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.co.ceiba.parqueadero.entidad.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>, JpaSpecificationExecutor<Carro> {
	
}
