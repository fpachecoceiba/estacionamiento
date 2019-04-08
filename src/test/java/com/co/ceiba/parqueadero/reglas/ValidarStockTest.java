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
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

public class ValidarStockTest {
	private static final String TIPO_CARRO = "CARRO";
	private static final String TIPO_MOTO = "MOTO";

	private static final Integer STOCK_CARRO_TRUE = 20;
	private static final Integer STOCK_CARRO_FALSE = 0;
	
	private static final Integer STOCK_MOTO_TRUE = 10;
	private static final Integer STOCK_MOTO_FALSE = 0;


	private String stringFecha = "2019-04-03 11:44:44";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime fechaEntrada = LocalDateTime.parse(stringFecha, formatter);


	@Test
	public void validarStockCarro() {
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoRepository);
		EntradaParqueoDTO entradaParqueoDTO = null;

		when(entradaParqueoRepository.listaActivas(TIPO_CARRO))
				.thenReturn(Arrays.asList(entradaParqueoDTO));
		
		assertEquals(validarStock.validarStock(TIPO_CARRO, STOCK_CARRO_TRUE), true);
		assertEquals(validarStock.validarStock(TIPO_CARRO, STOCK_CARRO_FALSE), false);
 
	}
	
	@Test
	public void validarStockMoto() {
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoRepository);
		Vehiculo vehiculo = null;

		when(entradaParqueoRepository.listaActivas(TIPO_MOTO))
				.thenReturn(Arrays.asList(new EntradaParqueoDTO(1l, fechaEntrada, true, vehiculo)));
		
		assertEquals(validarStock.validarStock(TIPO_MOTO, STOCK_MOTO_TRUE), true);
		assertEquals(validarStock.validarStock(TIPO_MOTO, STOCK_MOTO_FALSE), false);

	}
	
	

}
