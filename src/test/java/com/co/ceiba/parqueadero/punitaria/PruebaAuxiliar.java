package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PruebaAuxiliar {
	
	public static final String PLACA_TEST = "ACT-002";

	@Test
	public void prueba() {
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		vehiculoDTO.setPlaca(PLACA_TEST);
		assertEquals(PLACA_TEST, vehiculoDTO.getPlaca());
	}
	
}
