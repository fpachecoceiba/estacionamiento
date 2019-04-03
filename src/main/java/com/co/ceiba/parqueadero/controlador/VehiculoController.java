package com.co.ceiba.parqueadero.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@RestController
@RequestMapping("/apiv1/vehiculos")
public class VehiculoController {
	@Autowired
	private VehiculoService vehiculoService;

	@PostMapping("/carro")
	public VehiculoDTO guardarCarro(@RequestBody CarroDTO carroDTO) { 
		return vehiculoService.guardar(carroDTO);
	}

	@PostMapping("/moto")
	public VehiculoDTO guardarMoto(@RequestBody MotoDTO motoDTO) {
		return vehiculoService.guardar(motoDTO);
	}

	@GetMapping("/listarTipo")
	public List<VehiculoDTO> listar(@RequestParam String tipoVehiculo) {
		return vehiculoService.listarTipo(tipoVehiculo);
	}
	@GetMapping("/listarPlaca")
	public VehiculoDTO listarPlaca(@RequestParam String placa) {
		return vehiculoService.buscarPorPlaca(placa);
	}
	
}
