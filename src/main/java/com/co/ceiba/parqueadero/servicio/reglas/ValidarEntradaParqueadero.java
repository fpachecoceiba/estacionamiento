package com.co.ceiba.parqueadero.servicio.reglas;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;

public class ValidarEntradaParqueadero {
	public static final String MENSAJE = "Este vehiculo no esta autorizado a ingresar";
	public static final String MENSAJE_OK = "acceso ok ";
	public static final String MENSAJE_EXISTE = "Este vehiculo ya tiene una entrada registrada";


	private static final String INICIO_PLACA = "A";
	private EntradaParqueoService entradaParqueoService;

	public ValidarEntradaParqueadero(EntradaParqueoService entradaParqueoService) {
		this.entradaParqueoService = entradaParqueoService;
	}

	public boolean ingresoValidoSegunDiaPlaca(String placa, LocalDateTime fechaEntrada) {
		if (placa.toUpperCase().startsWith(INICIO_PLACA)) {
			DayOfWeek diaSemana = fechaEntrada.getDayOfWeek();
			if (diaSemana != DayOfWeek.SUNDAY && diaSemana != DayOfWeek.MONDAY) {
				return false;
			}
		}
		return true;

	}

	public boolean existeEntradaRegistrada(String placa) {
		
		return entradaParqueoService.consultarActivaPorId(placa) != null;
	}

}
