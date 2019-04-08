package com.co.ceiba.parqueadero.servicio.reglas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.TipoCobro;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;

public class CalcularTarifaSalida {

	public static final Integer RANGO_HORA_INFERIOR = 9;
	public static final Integer RANGO_HORA_SUPERIOR = 24;

	public static final Double CILINDRAJE_MAX = 500.0; 
	public static final Double VALOR_EXCEDENTE = 2000.0;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static String stringFecha = "2019-04-04 01:00:00";
	private static LocalDateTime toDateTime = LocalDateTime.parse(stringFecha, formatter);

	private static String stringfromDateTime = "2019-04-05 04:00:00";
	private static LocalDateTime fromDateTime = LocalDateTime.parse(stringfromDateTime, formatter);

	private TarifaRepository tarifaRepository;

	public CalcularTarifaSalida(TarifaRepository tarifaRepository) {
		this.tarifaRepository = tarifaRepository;
	}

	public Double getValorTarifa(String tipoVehiculo, Double cilindraje,LocalDateTime toDateTime,LocalDateTime fromDateTime ) {
		Integer dias = 0;
		Integer horas = 0;
		Long tiempoDuracion = getHora(toDateTime, fromDateTime);

		if (tiempoDuracion > RANGO_HORA_SUPERIOR) {
			dias = tiempoDuracion.intValue() / RANGO_HORA_SUPERIOR;
			horas = tiempoDuracion.intValue() % RANGO_HORA_SUPERIOR;
			if (horas > RANGO_HORA_SUPERIOR) {
				dias++;
				horas = 0;
			}
		} else if (tiempoDuracion > RANGO_HORA_INFERIOR) {
			dias++;
			horas = 0;
		} else {
			horas = tiempoDuracion.intValue();
		}

		Double valorHoras = getTarifa(TipoCobro.HORA.toString(), tipoVehiculo) * horas;
		Double valorDias = getTarifa(TipoCobro.DIA.toString(), tipoVehiculo) * dias;
		Double excedente = getExedenteMoto(cilindraje, tipoVehiculo);

		return valorDias + valorHoras + excedente;
	} 

	public Long getHora(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		ZonedDateTime zdt2 = fechaEntrada.atZone(ZoneId.systemDefault());
		Date d2 = Date.from(zdt2.toInstant());
		ZonedDateTime zdt1 = fechaSalida.atZone(ZoneId.systemDefault());
		Date d1 = Date.from(zdt1.toInstant());
		long diff = d1.getTime() - d2.getTime();
		return diff / (60 * 60 * 1000);
	}
 
	public Double getTarifa(String modalidad, String tipoVehiculo) {
		return tarifaRepository.consultarTarifa(modalidad, tipoVehiculo).get(0).getValor();

	}

	public Double getExedenteMoto(Double cilindraje, String tipoVehiculo) {
		if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			if (cilindraje > CILINDRAJE_MAX) {
				return VALOR_EXCEDENTE;
			} else {
				return 0.0; 
			}
		}
		return 0.0;
	}

}
