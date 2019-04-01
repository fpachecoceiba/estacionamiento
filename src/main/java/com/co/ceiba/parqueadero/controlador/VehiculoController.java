package com.co.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@RestController
@RequestMapping("/apiv1/vehiculos")
public class VehiculoController {
	@Autowired
	private VehiculoService vehiculoService;
	@Autowired
	private EntradaParqueoService entradaParqueoService;

	@PostMapping("/carro")
	public VehiculoDTO guardarCarro(@RequestBody CarroDTO carroDTO) {
		return vehiculoService.guardar(carroDTO);
	}
//	
	@PostMapping("/moto")
	public VehiculoDTO guardarMoto(@RequestBody MotoDTO motoDTO) {
		return vehiculoService.guardar(motoDTO);
	}
	
	@PostMapping("/ingreso")
	public Object registrarIngreso(@RequestBody EntradaParqueoDTO entradaParqueoDTO) {
		return entradaParqueoService.registrar(entradaParqueoDTO);
	}


}
