package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehiculoTest {
	private static final String PLACA = "PL0001";
	private static final String MODELO = "2019";
	private static final Double CILINDRAJE = 150.0;
	private static final Long ID_VEHICULO = 1L;
	private static final Long ID_CARRO = 1L;
	private static final Long ID_MOTO = 1L;

	@Test
	public void guardarVehiculoCarro() {
		CarroDTO carroDTO = new CarroDTO(ID_CARRO, MODELO, ID_VEHICULO, PLACA, TipoVehiculo.CARRO.toString());
		assertEquals(PLACA, carroDTO.getPlaca());
		assertEquals(MODELO, carroDTO.getModelo());
		assertEquals(TipoVehiculo.CARRO.toString(), carroDTO.getTipoVehiculo());
		assertEquals(ID_CARRO, carroDTO.getIdCarro());
		assertEquals(ID_VEHICULO, carroDTO.getIdVehiculo());

	}

	@Test
	public void guardarVehiculoMoto() {
		MotoDTO motoDTO = new MotoDTO(ID_MOTO, CILINDRAJE, ID_VEHICULO, PLACA, TipoVehiculo.MOTO.toString());
		assertEquals(PLACA, motoDTO.getPlaca());
		assertEquals(CILINDRAJE, motoDTO.getCilindraje());
		assertEquals(TipoVehiculo.MOTO.toString(), motoDTO.getTipoVehiculo());
		assertEquals(ID_CARRO, motoDTO.getIdMoto());
		assertEquals(ID_VEHICULO, motoDTO.getIdVehiculo());

	}

}
