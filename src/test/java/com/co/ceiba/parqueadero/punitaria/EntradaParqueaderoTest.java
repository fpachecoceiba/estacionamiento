package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.CarroBuilder;
import com.co.ceiba.parqueadero.builder.MotoBuilder;
import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EntradaParqueaderoTest {


	private static final String PLACA = "PL0001";
	private static final Double CILINDRAJE = 650.0;
	private static final String MODELO = "2019";
 
	private LocalDateTime fechaEntrada = LocalDateTime.now();

	@Test
	public void registrarCarro() {  
 
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTO(PLACA, TipoVehiculo.CARRO.toString());
		when(vehiculoRepository.findByPlaca(PLACA)).thenReturn(vehiculoDTO);

		CarroRepository carroRepository = mock(CarroRepository.class);
		CarroDTO carroDTO = new CarroDTO(MODELO, PLACA, TipoVehiculo.CARRO.toString());
		when(carroRepository.findById(PLACA)).thenReturn(carroDTO);

		MotoRepository motoRepository = mock(MotoRepository.class);
		EntradaParqueoDTO entradaParqueoDTOCarro = new EntradaParqueoDTO(1l, fechaEntrada, Boolean.TRUE,
				CarroBuilder.getCarroEntidad(carroDTO));
		
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		when(entradaParqueoRepository.guardar(Mockito.any(EntradaParqueoDTO.class))).thenReturn(entradaParqueoDTOCarro); 
 
		EntradaParqueoService entradaParqueoService = new EntradaParqueoService(entradaParqueoRepository,
				vehiculoRepository, carroRepository, motoRepository);
		
		EntradaParqueoDTO entradaParqueoDTO2 = entradaParqueoService.registrar(vehiculoDTO);
		assertTrue(entradaParqueoDTO2.getActivo()); 

	}
	
	@Test
	public void registrarMoto() { 

		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTO(PLACA, TipoVehiculo.MOTO.toString());
		when(vehiculoRepository.findByPlaca(PLACA)).thenReturn(vehiculoDTO);

		MotoRepository motoRepository = mock(MotoRepository.class);
		CarroRepository carroRepository = mock(CarroRepository.class);
		MotoDTO motoDTO = new MotoDTO(CILINDRAJE, PLACA, TipoVehiculo.MOTO.toString());
		when(motoRepository.findByPlaca(PLACA)).thenReturn(motoDTO);

		
		EntradaParqueoDTO entradaParqueoDTOCarro = new EntradaParqueoDTO(1l, fechaEntrada, Boolean.TRUE,
				MotoBuilder.getEntidad(motoDTO));
		
		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		when(entradaParqueoRepository.guardar(Mockito.any(EntradaParqueoDTO.class))).thenReturn(entradaParqueoDTOCarro); 
 
		EntradaParqueoService entradaParqueoService = new EntradaParqueoService(entradaParqueoRepository,
				vehiculoRepository, carroRepository, motoRepository);
		
		EntradaParqueoDTO entradaParqueoDTO2 = entradaParqueoService.registrar(vehiculoDTO);
		assertTrue(entradaParqueoDTO2.getActivo());

	} 

}
