package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.EntradaParqueaderoBuilder;
import com.co.ceiba.parqueadero.builder.SalidaParqueaderoBuilder;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;
import com.co.ceiba.parqueadero.entidad.Vehiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SalidaParqueaderoBuilderTest {

	private static final Long ID_ENTRADA = 1L;
	private static final Long ID_SALIDA = 1L;
	private static final Long ID_VEHICULO = 1L;
	private static final String PLACA = "PL0001";
	private static final Double VALOR = 11000.0;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static final String STRING_FECHA_ENTRADA = "2019-04-04 01:00:00";
	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.parse(STRING_FECHA_ENTRADA, FORMATTER);
	private static final String STRING_FECHA_SALIDA = "2019-04-04 01:00:00";
	private static final LocalDateTime FECHA_SALIDA = LocalDateTime.parse(STRING_FECHA_SALIDA, FORMATTER);

	@Test
	public void salidaBuilderDTO() {
		EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO();
		entradaParqueoDTO.setIdEntrada(ID_ENTRADA);
		entradaParqueoDTO.setActivo(Boolean.TRUE);
		entradaParqueoDTO.setFechaEntrada(FECHA_ENTRADA);
		entradaParqueoDTO.setIdVehiculo(new Vehiculo(ID_VEHICULO, PLACA, TipoVehiculo.CARRO.toString()));

		SalidaParqueadero salidaParqueadero = new SalidaParqueadero();
		salidaParqueadero.setEntradaParqueo(EntradaParqueaderoBuilder.getEntidad(entradaParqueoDTO));
		salidaParqueadero.setFechaSalida(FECHA_SALIDA);
		salidaParqueadero.setIdSalida(ID_SALIDA);
		salidaParqueadero.setValor(VALOR);

		SalidaParqueaderoDTO salidaParqueaderoDTO = SalidaParqueaderoBuilder.getSalidaParqueaderoDTO(salidaParqueadero);

		assertEquals(Boolean.TRUE,salidaParqueaderoDTO.getEntradaParqueo().getActivo());
		assertEquals(VALOR, salidaParqueaderoDTO.getValor());
		
	}

}
