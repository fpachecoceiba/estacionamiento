package com.co.ceiba.parqueadero.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.SalidaParqueaderoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/apiv1/salidas")
public class SalidaParqueaderoController {
	private SalidaParqueaderoService salidaParqueaderoService;

	public SalidaParqueaderoController(SalidaParqueaderoService salidaParqueaderoService) {
		this.salidaParqueaderoService = salidaParqueaderoService;
	}

	@PostMapping
	public SalidaParqueaderoDTO registrar(@RequestBody VehiculoDTO vehiculoDTO) {
		return salidaParqueaderoService.registrar(vehiculoDTO);

	}

	@GetMapping
	public List<SalidaParqueaderoDTO> consultar(@RequestParam String placa) {
		return salidaParqueaderoService.consultar(placa);
	}

}
