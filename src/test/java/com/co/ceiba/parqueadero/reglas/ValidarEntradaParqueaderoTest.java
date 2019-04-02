package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;

public class ValidarEntradaParqueaderoTest {
	public static final String PLACA_VALIDA = "BBB0123";
	public static final String PLACA_NO_VALIDA = "AB0123";

	private String stringFecha = "2019-04-03 11:44:44";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime fechaEntrada = LocalDateTime.parse(stringFecha, formatter);

	@Test
	public void validarEntradaNoValida() {

		ValidarEntradaParqueadero validarEntradaParqueadero = new ValidarEntradaParqueadero();
		boolean valida = validarEntradaParqueadero.ingresoValidoSegunDiaPlaca(PLACA_NO_VALIDA, fechaEntrada);
		assertEquals(valida, false);

	}
	
	@Test
	public void validarEntradaValida() {

		ValidarEntradaParqueadero validarEntradaParqueadero = new ValidarEntradaParqueadero();
		boolean valida = validarEntradaParqueadero.ingresoValidoSegunDiaPlaca(PLACA_VALIDA, fechaEntrada);
		assertEquals(valida, true);

	} 

}
