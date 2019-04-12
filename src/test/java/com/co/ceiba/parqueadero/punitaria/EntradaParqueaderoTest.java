package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.co.ceiba.parqueadero.dominio.excepcion.ParqueaderoNoDisponibleException;
import com.co.ceiba.parqueadero.repositorio.CarroRepository;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarEntradaParqueadero;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntradaParqueaderoTest {

	private static final String PLACA = "PL0001";
	private static final String PLACA_A = "APL0001";
	private static final Double CILINDRAJE = 650.0;
	private static final String MODELO = "2019";
	private String stringFecha = "2019-04-03 11:44:44";
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime fecha = LocalDateTime.parse(stringFecha, formatter);

	private static final String MENSAJE_NOEXISTE_VEHICULO = "No existe un vehiculo registrado para esta informacion";

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
				CarroBuilder.getEntidad(carroDTO));

		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		when(entradaParqueoRepository.guardar(Mockito.any(EntradaParqueoDTO.class))).thenReturn(entradaParqueoDTOCarro);

		EntradaParqueoService entradaParqueoService = new EntradaParqueoService(entradaParqueoRepository,
				vehiculoRepository, carroRepository, motoRepository);

		EntradaParqueoDTO entradaParqueoDTO2 = entradaParqueoService.registrar(vehiculoDTO, fecha);
		
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

		EntradaParqueoDTO entradaParqueoDTO2 = entradaParqueoService.registrar(vehiculoDTO, fecha);

		assertTrue(entradaParqueoDTO2.getActivo());

	}

	@Test
	public void registrarMotoNull() {

		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTO("0125422", TipoVehiculo.MOTO.toString());
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

		try {
			entradaParqueoService.registrar(vehiculoDTO, fecha);
		} catch (ParqueaderoNoDisponibleException e) {
			assertEquals(e.getMessage(), MENSAJE_NOEXISTE_VEHICULO);

		}

	}

	@Test
	public void validarIngresoPlacaFecha() {

		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTO(PLACA_A, TipoVehiculo.MOTO.toString());
		when(vehiculoRepository.findByPlaca(PLACA_A)).thenReturn(vehiculoDTO);

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

		try {
			entradaParqueoService.registrar(vehiculoDTO, fecha);
		} catch (ParqueaderoNoDisponibleException e) {

			assertEquals(e.getMessage(), ValidarEntradaParqueadero.MENSAJE);

		}

	}
	
	

}
