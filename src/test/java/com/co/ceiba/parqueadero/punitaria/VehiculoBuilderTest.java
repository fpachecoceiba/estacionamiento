package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.VehiculoBuilder;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Vehiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehiculoBuilderTest {


	private static final String PLACA = "PC001";

	@Test
	public void vehiculoBuilderDTO() {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setPlaca(PLACA);
		vehiculo.setTipoVehiculo(TipoVehiculo.CARRO.toString());

		VehiculoDTO vehiculoDTO  =  VehiculoBuilder.getVehiculoDTO(vehiculo);

		assertEquals(PLACA, vehiculoDTO.getPlaca());
		assertEquals(TipoVehiculo.CARRO.toString(), vehiculoDTO.getTipoVehiculo());
		
	}

	@Test
	public void vehiculoBuilderEntidad() {
		VehiculoDTO vehiculoDTO = new  VehiculoDTO();
		vehiculoDTO.setPlaca(PLACA);
		vehiculoDTO.setTipoVehiculo(TipoVehiculo.CARRO.toString());
		Vehiculo vehiculo = VehiculoBuilder.getVehiculoEntidad(vehiculoDTO);
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(TipoVehiculo.CARRO.toString(), vehiculo.getTipoVehiculo());
		
	}
	
	@Test
	public void vehiculoBuilderDTONull() {
		Vehiculo vehiculo = null;
		VehiculoDTO vehiculoDTO = VehiculoBuilder.getVehiculoDTO(vehiculo);
		assertNull(vehiculoDTO);

	}

	@Test
	public void vehiculoBuilderEntidadNull() {
		VehiculoDTO vehiculoDTO = null;
		Vehiculo vehiculo = VehiculoBuilder.getVehiculoEntidad(vehiculoDTO);
		assertNull(vehiculo);

	}

}
