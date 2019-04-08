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
@RequestMapping("/apiv1/vehiculo")
public class VehiculoController {


	private VehiculoService vehiculoService;
 
	public VehiculoController(VehiculoService vehiculoService) {
		this.vehiculoService = vehiculoService;
	}
	

	@PostMapping("/carro")
	public VehiculoDTO guardarCarro(@RequestBody CarroDTO carroDTO) {
		return vehiculoService.guardar(carroDTO);
	}

	@CrossOrigin
	@PostMapping("/moto")
	public VehiculoDTO guardarMoto(@RequestBody MotoDTO motoDTO) {
		return vehiculoService.guardar(motoDTO);
	}

//	@GetMapping("/listarTipo")
//	public List<VehiculoDTO> listar(@RequestParam String tipoVehiculo) {
//		return vehiculoService.listarTipo(tipoVehiculo);
//	}
//
//	@GetMapping("/listarPlaca")
//	public VehiculoDTO listarPlaca(@RequestParam String placa) {
//		return vehiculoService.buscarPorPlaca(placa);
//	}

}
