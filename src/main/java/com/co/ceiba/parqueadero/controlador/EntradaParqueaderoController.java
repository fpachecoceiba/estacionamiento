package com.co.ceiba.parqueadero.controlador;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;

@RestController
@RequestMapping("/apiv1/entrada")
public class EntradaParqueaderoController {

	private EntradaParqueoService entradaParqueoService;

	public EntradaParqueaderoController(EntradaParqueoService entradaParqueoService) {
		this.entradaParqueoService = entradaParqueoService;
	}

	@PostMapping("/registrar")
	public EntradaParqueoDTO registrarIngreso(@RequestBody VehiculoDTO vehiculoDTO) {
		return entradaParqueoService.registrar(vehiculoDTO);
	}

}
