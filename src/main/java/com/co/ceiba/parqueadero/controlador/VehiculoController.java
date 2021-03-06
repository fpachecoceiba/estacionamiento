package com.co.ceiba.parqueadero.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.VehiculoService;


@RestController
@CrossOrigin("*")
@RequestMapping("/apiv1/vehiculos")
public class VehiculoController {


	private VehiculoService vehiculoService;
 
	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}
	

	@PostMapping("/carros")
	public VehiculoDTO guardarCarro(@RequestBody CarroDTO carroDTO) {
		return vehiculoService.guardar(carroDTO);
	}


	@PostMapping("/motos")
	public VehiculoDTO guardarMoto(@RequestBody MotoDTO motoDTO) {
		return vehiculoService.guardar(motoDTO);
	}


}
