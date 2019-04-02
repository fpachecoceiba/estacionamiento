package com.co.ceiba.parqueadero.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.co.ceiba.parqueadero.entidad.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>, JpaSpecificationExecutor<Vehiculo> {

	public Vehiculo findByPlaca(String placa);
	public List<Vehiculo> findByTipoVehiculo(String tipoVehiculo);
}
