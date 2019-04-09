package com.co.ceiba.parqueadero.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.SalidaParqueaderoService;

@RestController
@RequestMapping("/apiv1/salida")
public class SalidaParqueaderoController {
	private SalidaParqueaderoService salidaParqueaderoService;

	public SalidaParqueaderoController(SalidaParqueaderoService salidaParqueaderoService) {
		this.salidaParqueaderoService = salidaParqueaderoService;
	}

	@PostMapping("/registrar")
	public SalidaParqueaderoDTO registrar(@RequestBody VehiculoDTO vehiculoDTO) {
		return salidaParqueaderoService.registrar(vehiculoDTO);

	}

}
