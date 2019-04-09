package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.VehiculoBuilder;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.SalidaParqueaderoRepository;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.SalidaParqueaderoService;
import com.co.ceiba.parqueadero.servicio.reglas.CalcularTarifaSalida;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SalidaParqueaderoTest {

	private static final String TIPO_CARRO = "CARRO";
	private static final String TIPO_MOTO = "MOTO";

	private static final String MODALIDAD_HORA = "HORA";
	private static final String MODALIDAD_DIA = "DIA";
	private static final Double CILINDRAJE = 650.0;
	private static final Double SIN_CILINDRAJE = 0.0;

	private static final Double VALOR_ESPERADO_CARRO = 11000.0;
	private static final Double VALOR_ESPERADO_MOTO = 6000.0;
 
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	// datos de fechas para prueba carro un dia y tres horas retorno de 11000.0
	private static final String STRING_FECHA_ENTRADA_CARRO = "2019-04-04 01:00:00";
	private static final LocalDateTime FECHA_ENTRADA_CARRO = LocalDateTime.parse(STRING_FECHA_ENTRADA_CARRO, FORMATTER);

	private static final String STRING_FECHA_SALIDA_CARRO = "2019-04-05 04:00:00";
	private static final LocalDateTime FECHA_SALIDA_CARRO = LocalDateTime.parse(STRING_FECHA_SALIDA_CARRO, FORMATTER);

	// datos de fechas para prueba motos diez horas retorno de 6000.0
	private static final String STRING_FECHA_ENTRADA_MOTO = "2019-04-05 01:00:00";
	private static final LocalDateTime FECHA_ENTRADA_MOTO = LocalDateTime.parse(STRING_FECHA_ENTRADA_MOTO, FORMATTER);

	private static final String STRING_FECHA_SALIDA_MOTO = "2019-04-05 11:00:00";
	private static final LocalDateTime FECHA_SALIDA_MOTO = LocalDateTime.parse(STRING_FECHA_SALIDA_MOTO, FORMATTER);

	private static final Double VALOR_DIA_CARRO = 8000.0;
	private static final Double VALOR_HORA_CARRO = 1000.0;

	private static final Double VALOR_DIA_MOTO = 4000.0;
	private static final Double VALOR_HORA_MOTO = 500.0;

	private static final Long ID_ENTRADA = 1L;
	private static final Long ID_SALIDA = 1L;
	private static final String PLACA = "PL0001";
	private static final LocalDateTime FECHA_ENTRADA = LocalDateTime.now();

	private static final LocalDateTime FECHA_SALIDA = LocalDateTime.now();

	@Test
	public void registrarCarro() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		MotoRepository motoRepository = mock(MotoRepository.class);

		VehiculoDTO vehiculoDTO = new VehiculoDTO(PLACA, TipoVehiculo.CARRO.toString());
		when(vehiculoRepository.findByPlaca(PLACA)).thenReturn(vehiculoDTO);

		EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO(ID_ENTRADA, FECHA_ENTRADA, Boolean.TRUE,
				VehiculoBuilder.getVehiculoEntidad(vehiculoDTO));

		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		when(entradaParqueoRepository.consultarActivaPorPlaca(PLACA)).thenReturn(Arrays.asList(entradaParqueoDTO));

		TarifaRepository tarifaRepository = mock(TarifaRepository.class);
		CalcularTarifaSalida calcularTarifaSalida = new CalcularTarifaSalida(tarifaRepository);
		TarifaDTO tarifaDTO = new TarifaDTO(MODALIDAD_DIA, VALOR_DIA_CARRO, TIPO_CARRO);
		TarifaDTO tarifaDTO2 = new TarifaDTO(MODALIDAD_HORA, VALOR_HORA_CARRO, TIPO_CARRO);

		when(tarifaRepository.consultarTarifa(MODALIDAD_DIA, TIPO_CARRO)).thenReturn(Arrays.asList(tarifaDTO));
		when(tarifaRepository.consultarTarifa(MODALIDAD_HORA, TIPO_CARRO)).thenReturn(Arrays.asList(tarifaDTO2));

		Double valor = calcularTarifaSalida.getValorTarifa(TIPO_CARRO, SIN_CILINDRAJE, FECHA_ENTRADA_CARRO,
				FECHA_SALIDA_CARRO);

		SalidaParqueaderoDTO salidaParqueaderoDTO = new SalidaParqueaderoDTO(ID_SALIDA, FECHA_SALIDA, entradaParqueoDTO,
				valor);

		SalidaParqueaderoRepository salidaParqueaderoRepository = mock(SalidaParqueaderoRepository.class);
		when(salidaParqueaderoRepository.registrar(Mockito.any(SalidaParqueaderoDTO.class)))
				.thenReturn(salidaParqueaderoDTO);

		SalidaParqueaderoService salidaParqueaderoService = new SalidaParqueaderoService(salidaParqueaderoRepository,
				entradaParqueoRepository, vehiculoRepository, tarifaRepository, motoRepository);

		SalidaParqueaderoDTO salidaParqueaderoDTO2 = salidaParqueaderoService.registrar(vehiculoDTO);
		assertEquals(salidaParqueaderoDTO2.getValor(), VALOR_ESPERADO_CARRO);

	}

	@Test
	public void registrarMoto() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		MotoRepository motoRepository = mock(MotoRepository.class);
		VehiculoDTO vehiculoDTO = new VehiculoDTO( PLACA, TipoVehiculo.MOTO.toString());
		when(vehiculoRepository.findByPlaca(PLACA)).thenReturn(vehiculoDTO);

		EntradaParqueoDTO entradaParqueoDTO = new EntradaParqueoDTO(ID_ENTRADA, FECHA_ENTRADA, Boolean.TRUE,
				VehiculoBuilder.getVehiculoEntidad(vehiculoDTO));

		EntradaParqueoRepository entradaParqueoRepository = mock(EntradaParqueoRepository.class);
		when(entradaParqueoRepository.consultarActivaPorPlaca(PLACA)).thenReturn(Arrays.asList(entradaParqueoDTO));

		TarifaRepository tarifaRepository = mock(TarifaRepository.class);
		CalcularTarifaSalida calcularTarifaSalida = new CalcularTarifaSalida(tarifaRepository);
		TarifaDTO tarifaDTO = new TarifaDTO(MODALIDAD_DIA, VALOR_DIA_MOTO, TIPO_MOTO);
		TarifaDTO tarifaDTO2 = new TarifaDTO(MODALIDAD_HORA, VALOR_HORA_MOTO, TIPO_MOTO);

		when(tarifaRepository.consultarTarifa(MODALIDAD_DIA, TIPO_MOTO)).thenReturn(Arrays.asList(tarifaDTO));
		when(tarifaRepository.consultarTarifa(MODALIDAD_HORA, TIPO_MOTO)).thenReturn(Arrays.asList(tarifaDTO2));

		Double valor = calcularTarifaSalida.getValorTarifa(TIPO_MOTO, CILINDRAJE, FECHA_ENTRADA_MOTO,
				FECHA_SALIDA_MOTO);

		SalidaParqueaderoDTO salidaParqueaderoDTO = new SalidaParqueaderoDTO(ID_SALIDA, FECHA_SALIDA, entradaParqueoDTO,
				valor);

		SalidaParqueaderoRepository salidaParqueaderoRepository = mock(SalidaParqueaderoRepository.class);
		when(salidaParqueaderoRepository.registrar(Mockito.any(SalidaParqueaderoDTO.class)))
				.thenReturn(salidaParqueaderoDTO);

		SalidaParqueaderoService salidaParqueaderoService = new SalidaParqueaderoService(salidaParqueaderoRepository,
				entradaParqueoRepository, vehiculoRepository, tarifaRepository, motoRepository);

		MotoDTO motoDTO = new MotoDTO(CILINDRAJE, PLACA, TipoVehiculo.MOTO.toString());
		when(motoRepository.findByPlaca(PLACA)).thenReturn(motoDTO);
		SalidaParqueaderoDTO salidaParqueaderoDTO2 = salidaParqueaderoService.registrar(motoDTO);
		assertEquals(salidaParqueaderoDTO2.getValor(), VALOR_ESPERADO_MOTO);

	}

}
