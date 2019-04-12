package com.co.ceiba.parqueadero.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/apiv1/entradas")
public class EntradaParqueaderoController {

	private EntradaParqueoService entradaParqueoService;

	public EntradaParqueaderoController(EntradaParqueoService entradaParqueoService) {
		this.entradaParqueoService = entradaParqueoService;
	}

	@PostMapping
	public EntradaParqueoDTO registrarIngreso(@RequestBody VehiculoDTO vehiculoDTO) {
		LocalDateTime fechaEntrada = LocalDateTime.now();
		return entradaParqueoService.registrar(vehiculoDTO, fechaEntrada);
	}

	@GetMapping
	public List<EntradaParqueoDTO> listaTodas(@RequestParam(defaultValue = "true") Boolean activo,
			@RequestParam Optional<String> tipo, @RequestParam Optional<String> placa) {
		return entradaParqueoService.listarTodas(activo, tipo, placa);

	}

}
