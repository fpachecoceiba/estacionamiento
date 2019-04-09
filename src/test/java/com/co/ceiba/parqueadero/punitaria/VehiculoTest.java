package com.co.ceiba.parqueadero.punitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.builder.CarroBuilder;
import com.co.ceiba.parqueadero.builder.MotoBuilder;
import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Carro;
import com.co.ceiba.parqueadero.entidad.Moto;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.VehiculoService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VehiculoTest {
	private static final String PLACA = "PL0001";
	private static final String MODELO = "2019";
	private static final Double CILINDRAJE = 650.0;
 
	@Test
	public void guardarVehiculoCarro() {
		CarroDTO carroDTO = new CarroDTO(MODELO, PLACA, TipoVehiculo.CARRO.toString());
		assertEquals(PLACA, carroDTO.getPlaca());
		assertEquals(MODELO, carroDTO.getModelo());
		assertEquals(TipoVehiculo.CARRO.toString(), carroDTO.getTipoVehiculo());
		

	} 

	@Test
	public void guardarVehiculoMoto() {
		MotoDTO motoDTO = new MotoDTO(CILINDRAJE, PLACA, TipoVehiculo.MOTO.toString());
		assertEquals(PLACA, motoDTO.getPlaca());
		assertEquals(CILINDRAJE, motoDTO.getCilindraje());
		assertEquals(TipoVehiculo.MOTO.toString(), motoDTO.getTipoVehiculo());
	
	}
 
	@Test
	public void registrarVehiculoCarro() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);

		when(vehiculoRepository.registrar(Mockito.any(VehiculoDTO.class)))
				.thenReturn(new VehiculoDTO( PLACA, TipoVehiculo.CARRO.toString()));

		VehiculoService vehiculoService = new VehiculoService(vehiculoRepository);
		VehiculoDTO vehiculoDTO = new CarroDTO(MODELO, PLACA, TipoVehiculo.CARRO.toString());
		VehiculoDTO vehiculoDTO2 = vehiculoService.guardar(vehiculoDTO);

		assertEquals(vehiculoDTO2.getPlaca(), PLACA);

	}

	@Test
	public void registrarVehiculoMoto() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);

		when(vehiculoRepository.registrar(Mockito.any(VehiculoDTO.class)))
				.thenReturn(new VehiculoDTO(PLACA, TipoVehiculo.MOTO.toString()));

		VehiculoService vehiculoService = new VehiculoService(vehiculoRepository);
		VehiculoDTO vehiculoDTO = new MotoDTO(CILINDRAJE, PLACA, TipoVehiculo.MOTO.toString());
		VehiculoDTO vehiculoDTO2 = vehiculoService.guardar(vehiculoDTO);

		assertEquals(vehiculoDTO2.getPlaca(), PLACA);

	} 

	@Test
	public void listarPorTipo() {
		VehiculoRepository vehiculoRepository = mock(VehiculoRepository.class);
		when(vehiculoRepository.listarPorTipo(TipoVehiculo.CARRO.toString()))
				.thenReturn(Arrays.asList(new VehiculoDTO( PLACA, TipoVehiculo.CARRO.toString())));
		VehiculoService vehiculoService = new VehiculoService(vehiculoRepository);
		List<VehiculoDTO> listaVehiculo = vehiculoService.listarPorTipo(TipoVehiculo.CARRO.toString());
		assertEquals(listaVehiculo.size(), 1);

	}
	
	
	

	@Test
	public void carroBuilderEntidad() {
		CarroDTO carroDTO = new CarroDTO(MODELO, PLACA, TipoVehiculo.CARRO.toString());
		Carro carro = CarroBuilder.getCarroEntidad(carroDTO);
		assertEquals(PLACA, carro.getPlaca());
		assertEquals(MODELO, carro.getModelo());
		assertEquals(TipoVehiculo.CARRO.toString(), carro.getTipoVehiculo());
		
	}

	@Test
	public void carroBuilderDTO() {
		Carro carro = new Carro();
		carro.setModelo(MODELO);
		carro.setPlaca(PLACA);
		carro.setTipoVehiculo(TipoVehiculo.CARRO.toString());

		CarroDTO carroDTO = CarroBuilder.getCarroDTO(carro);

		assertEquals(PLACA, carroDTO.getPlaca());
		assertEquals(MODELO, carroDTO.getModelo());
		assertEquals(TipoVehiculo.CARRO.toString(), carroDTO.getTipoVehiculo());
		
	}

	@Test
	public void motoBuilderEntidad() {
		MotoDTO motoDTO = new MotoDTO(CILINDRAJE, PLACA, TipoVehiculo.MOTO.toString());
		Moto moto = MotoBuilder.getEntidad(motoDTO);
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(CILINDRAJE, moto.getCilindraje());
		assertEquals(TipoVehiculo.MOTO.toString(), moto.getTipoVehiculo());
		
	}

	@Test
	public void motoBuilderDTO() {
		Moto moto = new Moto();
		moto.setCilindraje(CILINDRAJE);
		moto.setPlaca(PLACA);
		moto.setTipoVehiculo(TipoVehiculo.MOTO.toString());

		MotoDTO motoDTO = MotoBuilder.getMotoDTO(moto);

		assertEquals(PLACA, motoDTO.getPlaca());
		assertEquals(CILINDRAJE, motoDTO.getCilindraje());
		assertEquals(TipoVehiculo.MOTO.toString(), motoDTO.getTipoVehiculo());
		
	}

	@Test
	public void carroBuilderDTONull() {
		Carro carro = null;
		CarroDTO carroDTO = CarroBuilder.getCarroDTO(carro);
		assertNull(carroDTO);

	}

	@Test
	public void carroBuilderEntidadNull() {
		CarroDTO carroDTO = null;
		Carro carro = CarroBuilder.getCarroEntidad(carroDTO);
		assertNull(carro);

	}

	@Test
	public void motoBuilderDTONull() {
		Moto moto = null;
		MotoDTO motoDTO = MotoBuilder.getMotoDTO(moto);
		assertNull(motoDTO);

	} 

	@Test
	public void motoBuilderEntidadNull() {
		MotoDTO motoDTO = null;
		Moto moto = MotoBuilder.getEntidad(motoDTO);
		assertNull(moto);

	}

}
