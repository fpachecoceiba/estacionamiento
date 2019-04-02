package com.co.ceiba.parqueadero.servicio.reglas;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidarEntradaParqueadero {
	public static final String MENSAJE = "Este vehiculo no esta autorizado a ingresar";
	public static final String MENSAJE_OK = "acceso ok ";

	private static final String INICIO_PLACA = "A";

	public boolean ingresoValidoSegunDiaPlaca(String placa, LocalDateTime fechaEntrada) {
		if (placa.toUpperCase().startsWith(INICIO_PLACA)) {
			DayOfWeek diaSemana = fechaEntrada.getDayOfWeek();
			if (diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.MONDAY) {
				return false;

			}
		}
		return true;

	}

}
