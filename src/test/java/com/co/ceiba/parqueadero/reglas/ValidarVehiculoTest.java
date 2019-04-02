package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ExisteVehiculoException;
import com.co.ceiba.parqueadero.servicio.VehiculoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

public class ValidarVehiculoTest {
	public static final String PLACA = "PL0021";
	public VehiculoDTO  vehiculoDTO = new VehiculoDTO(1l,"00021",TipoVehiculo.CARRO.toString());
	@Test
	public void validarVehiculoCarro() {
		VehiculoService vehiculoService = mock(VehiculoService.class);
		ValidarVehiculo validarVehiculo = new ValidarVehiculo(vehiculoService);
		VehiculoDTO vehiculoDTO = null;
		
		when(vehiculoService.buscarPorPlaca(PLACA)).thenReturn(vehiculoDTO);
		try {
			validarVehiculo.verificar(PLACA);
		} catch (ExisteVehiculoException e) {
			assertEquals(e.getMessage(), ValidarVehiculo.MENSAJE);   
		} 
		 
	}
	
	@Test
	public void validarVehiculoCarronNull() {
		VehiculoService vehiculoService = mock(VehiculoService.class);
		ValidarVehiculo validarVehiculo = new ValidarVehiculo(vehiculoService);
		
		when(vehiculoService.buscarPorPlaca(PLACA)).thenReturn(vehiculoDTO);
		try {
			validarVehiculo.verificar(PLACA);
		} catch (ExisteVehiculoException e) {
			assertEquals(e.getMessage(), ValidarVehiculo.MENSAJE);   
		} 
		 
	}

}
