package com.co.ceiba.parqueadero.reglas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;

import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.ExisteVehiculoException;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;
import com.co.ceiba.parqueadero.servicio.reglas.CalcularTarifaSalida;
import com.co.ceiba.parqueadero.servicio.reglas.ValidarVehiculo;

public class CalcularTarifaSalidaTest {

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

	@Test
	public void getValorTarifaCarro() {
		TarifaRepository tarifaRepository = mock(TarifaRepository.class);
		CalcularTarifaSalida calcularTarifaSalida = new CalcularTarifaSalida(tarifaRepository);
		TarifaDTO tarifaDTO = new TarifaDTO(MODALIDAD_DIA, VALOR_DIA_CARRO, TIPO_CARRO);
		TarifaDTO tarifaDTO2 = new TarifaDTO(MODALIDAD_HORA, VALOR_HORA_CARRO, TIPO_CARRO);

		when(tarifaRepository.consultarTarifa(MODALIDAD_DIA, TIPO_CARRO)).thenReturn(Arrays.asList(tarifaDTO));
		when(tarifaRepository.consultarTarifa(MODALIDAD_HORA, TIPO_CARRO)).thenReturn(Arrays.asList(tarifaDTO2));
 
		try {
			Double valor = calcularTarifaSalida.getValorTarifa(TIPO_CARRO, SIN_CILINDRAJE, FECHA_ENTRADA_CARRO,
					FECHA_SALIDA_CARRO);

			assertEquals(valor, VALOR_ESPERADO_CARRO);
		} catch (ExisteVehiculoException e) {
			assertEquals(e.getMessage(), ValidarVehiculo.MENSAJE);
		}

	}

	@Test
	public void getValorTarifaMoto() {
		TarifaRepository tarifaRepository = mock(TarifaRepository.class);
		CalcularTarifaSalida calcularTarifaSalida = new CalcularTarifaSalida(tarifaRepository);
		TarifaDTO tarifaDTO = new TarifaDTO(MODALIDAD_DIA, VALOR_DIA_MOTO, TIPO_MOTO);
		TarifaDTO tarifaDTO2 = new TarifaDTO(MODALIDAD_HORA, VALOR_HORA_MOTO, TIPO_MOTO);

		when(tarifaRepository.consultarTarifa(MODALIDAD_DIA, TIPO_MOTO)).thenReturn(Arrays.asList(tarifaDTO));
		when(tarifaRepository.consultarTarifa(MODALIDAD_HORA, TIPO_MOTO)).thenReturn(Arrays.asList(tarifaDTO2));

		try {
			Double valor = calcularTarifaSalida.getValorTarifa(TIPO_MOTO, CILINDRAJE, FECHA_ENTRADA_MOTO,
					FECHA_SALIDA_MOTO);

			Double valorCilindrajeMenor = calcularTarifaSalida.getValorTarifa(TIPO_MOTO, SIN_CILINDRAJE,
					FECHA_ENTRADA_MOTO, FECHA_SALIDA_MOTO);
			Double valorEsperado = VALOR_ESPERADO_MOTO - 2000.0;

			assertEquals(valor, VALOR_ESPERADO_MOTO);
			assertEquals(valorCilindrajeMenor, valorEsperado);

		} catch (ExisteVehiculoException e) {
			assertEquals(e.getMessage(), ValidarVehiculo.MENSAJE);
		}

	}

}
