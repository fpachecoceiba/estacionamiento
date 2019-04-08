package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ExisteVehiculoException;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

public class ValidarVehiculoTest {
	public static final String PLACA = "PL0021";
	
	@Test
	public void validarVehiculo() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		ValidarVehiculo validarVehiculo = new ValidarVehiculo(vehiculoRepository);
		when(vehiculoRepository.findByPlaca(PLACA)).thenReturn(Arrays
				.asList(new VehiculoDTO(1l,PLACA,TipoVehiculo.CARRO.toString())));
		try {  
			validarVehiculo.verificar(PLACA); 
		} catch (ExisteVehiculoException e) {  
			assertEquals(e.getMessage(), ValidarVehiculo.MENSAJE);   
		} 
	} 
	 
 
}
