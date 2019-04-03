package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarStock;

public class ValidarStockTest {
	private static final String TIPO_CARRO = "CARRO";
	private static final String TIPO_MOTO = "MOTO";
	
	private static final Integer STOCK_CARRO = 2;
	private static final Integer STOCK_MOTO = 3;
	
	private static final Integer STOCK_CARRO_NO_DISPONIBLE = 0;
	private static final Integer STOCK_MOTO_NO_DISPONIBLE = 0;
	  
	 

	@Test
	public void validarStockCarroOK() {
		EntradaParqueoService entradaParqueoService = mock(EntradaParqueoService.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoService);

		List<EntradaParqueoDTO> listaActivas = new ArrayList<>();  
		when(entradaParqueoService.listarActivas(TIPO_CARRO)).thenReturn(listaActivas);

		try {
			validarStock.validarStock(TIPO_CARRO,STOCK_CARRO); 
		} catch (ParqueaderoNoDisponibleException e) {
			assertEquals(e.getMessage(), ValidarStock.MENSAJE_CARRO); 
		}
	}
	
	@Test
	public void validarStockMotoOK() {
		EntradaParqueoService entradaParqueoService = mock(EntradaParqueoService.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoService);

		List<EntradaParqueoDTO> listaActivas = new ArrayList<>(); 
		when(entradaParqueoService.listarActivas(TIPO_MOTO)).thenReturn(listaActivas);

		try {
			validarStock.validarStock(TIPO_MOTO,STOCK_MOTO);
		} catch (ParqueaderoNoDisponibleException e) {
			assertEquals(e.getMessage(), ValidarStock.MENSAJE_MOTO); 
		}
	}
	
	
	@Test
	public void validarStockCarroNodisponible() {
		EntradaParqueoService entradaParqueoService = mock(EntradaParqueoService.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoService);

		List<EntradaParqueoDTO> listaActivas = new ArrayList<>(); 
		when(entradaParqueoService.listarActivas(TIPO_CARRO)).thenReturn(listaActivas);

		try {
			validarStock.validarStock(TIPO_CARRO,STOCK_CARRO_NO_DISPONIBLE); 
		} catch (ParqueaderoNoDisponibleException e) {
			assertEquals(e.getMessage(), ValidarStock.MENSAJE_CARRO);  
		}
	}
	
	
	@Test
	public void validarStockMotoNodisponible() {
		EntradaParqueoService entradaParqueoService = mock(EntradaParqueoService.class);
		ValidarStock validarStock = new ValidarStock(entradaParqueoService);

		List<EntradaParqueoDTO> listaActivas = new ArrayList<>(); 
		when(entradaParqueoService.listarActivas(TIPO_MOTO)).thenReturn(listaActivas);

		try {
			validarStock.validarStock(TIPO_MOTO,STOCK_MOTO_NO_DISPONIBLE); 
		} catch (ParqueaderoNoDisponibleException e) {
			assertEquals(e.getMessage(), ValidarStock.MENSAJE_MOTO);  
		}
	}



}
