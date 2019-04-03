package com.co.ceiba.parqueadero.punitaria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.EntradaParqueoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoServiceTest {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	@Autowired
	private EntradaParqueoRepository entradaParqueoRepository;
	@Autowired
	private EntradaParqueoService entradaParqueoService;
	
	
	@Test
	public void testRegistrar() {
		VehiculoDTO vehiculoDTO = new VehiculoDTO(1L, "AXTrtr", TipoVehiculo.CARRO.toString());
		entradaParqueoService.registrar(vehiculoDTO);
	}
	
}
