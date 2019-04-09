package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;

public class ValidarEntradaParqueaderoTest {
	public static final String PLACA_VALIDA = "BBB0123";
	public static final String PLACA_NO_VALIDA = "AB0123";

	private String stringFecha = "2019-04-03 11:44:44";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime fechaEntrada = LocalDateTime.parse(stringFecha, formatter);

	@Test
	public void validarEntradaNoValida() {
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		ValidarEntradaParqueadero validarEntradaParqueadero = new ValidarEntradaParqueadero(entradaParqueoRepository);
		boolean valida = validarEntradaParqueadero.ingresoValidoSegunDiaPlaca(PLACA_NO_VALIDA, fechaEntrada);
		assertEquals(valida, false);

	} 

	@Test
	public void validarEntradaValida() {
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		ValidarEntradaParqueadero validarEntradaParqueadero = new ValidarEntradaParqueadero(entradaParqueoRepository);
		boolean valida = validarEntradaParqueadero.ingresoValidoSegunDiaPlaca(PLACA_VALIDA, fechaEntrada);
		assertEquals(valida, true);

	}

	@Test
	public void existeEntradaRegistradaTrue() { 
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		ValidarEntradaParqueadero validarEntradaParqueadero = new ValidarEntradaParqueadero(entradaParqueoRepository);
		Vehiculo vehiculo = null;

		when(entradaParqueoRepository.consultarActivaPorPlaca(PLACA_VALIDA))
				.thenReturn(Arrays.asList(new EntradaParqueoDTO(1l, fechaEntrada, true, vehiculo)));

		assertEquals(validarEntradaParqueadero.existeEntradaRegistrada(PLACA_VALIDA), true); 

	}

}
