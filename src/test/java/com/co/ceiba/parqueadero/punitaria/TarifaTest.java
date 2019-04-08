package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.CarroBuilder;
import com.co.ceiba.parqueadero.builder.TarifaBuilder;
import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.Tarifa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TarifaTest {

	private static final String MODALIDAD = "HORA";
	private static final Double VALOR = 11000.0;

	@Test
	public void tarifaBuilderDTO() {
		Tarifa tarifa = new Tarifa();
		tarifa.setModalidad(MODALIDAD);
		tarifa.setTipoVehiculo(TipoVehiculo.CARRO.toString());
		tarifa.setValor(VALOR);

		TarifaDTO tarifaDTO = TarifaBuilder.getTarifaDTO(tarifa);
		assertEquals(MODALIDAD, tarifaDTO.getModalidad());
		assertEquals(VALOR, tarifaDTO.getValor());
		assertEquals(TipoVehiculo.CARRO.toString(), tarifaDTO.getTipoVehiculo());

	}

	@Test
	public void tarifaBuilderDTONull() {
		Tarifa tarifa = null;
		TarifaDTO tarifaDTO = TarifaBuilder.getTarifaDTO(tarifa);
		assertNull(tarifaDTO);

	}

}
