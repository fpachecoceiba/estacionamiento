package com.co.ceiba.parqueadero.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@RestController
@RequestMapping("/apiv1/vehiculos")
public class VehiculoController {
	@Autowired
	private VehiculoService vehiculoService;

	@PostMapping("/carro")
	public Object guardarCarro(@RequestBody CarroDTO carroDTO) {
		return vehiculoService.guardar(carroDTO);
	}
	
	@PostMapping("/moto")
	public Object guardarMoto(@RequestBody MotoDTO motoDTO) {
		return vehiculoService.guardar(motoDTO);
	}


}
