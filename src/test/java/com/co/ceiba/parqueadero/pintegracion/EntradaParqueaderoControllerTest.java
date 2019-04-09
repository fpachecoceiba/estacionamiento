package com.co.ceiba.parqueadero.pintegracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaParqueaderoControllerTest {
	private static final String URL_ENTRADA = "/apiv1/entrada/registrar";
	private static final String URL_SALIDA = "/apiv1/salida/registrar";
	private static final String URL_CARRO = "/apiv1/vehiculo/carro";
	private static final String URL_MOTO = "/apiv1/vehiculo/moto";
	private static final String PLACA_CARRO = "PCL00288";
	private static final String PLACA_MOTO = "PCL001";
	private static final String MODELO = "2019";
	private static final Double CILINDRAJE = 11000.0;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void registrarCarro() throws Exception {
		CarroDTO carroDTO = new CarroDTO(MODELO, PLACA_CARRO, TipoVehiculo.CARRO.toString());
		mockMvc.perform(
				post(URL_CARRO).contentType(MediaType.APPLICATION_JSON).content(asJsonString(carroDTO)))
				.andExpect(status().isOk());

		mockMvc.perform(
				post(URL_ENTRADA).contentType(MediaType.APPLICATION_JSON).content(asJsonString(carroDTO)))
				.andExpect(status().isOk()); 
		
		mockMvc.perform(
				post(URL_SALIDA).contentType(MediaType.APPLICATION_JSON).content(asJsonString(carroDTO)))
				.andExpect(status().isOk()); 
	 

	}
 
	@Test
	public void registrarMoto() throws Exception {
		MotoDTO motoDTO = new MotoDTO(CILINDRAJE, PLACA_MOTO, TipoVehiculo.MOTO.toString());
		mockMvc.perform(post(URL_MOTO).contentType(MediaType.APPLICATION_JSON).content(asJsonString(motoDTO)))
				.andExpect(status().isOk());
		mockMvc.perform(
				post(URL_ENTRADA).contentType(MediaType.APPLICATION_JSON).content(asJsonString(motoDTO)))
				.andExpect(status().isOk());
		
		mockMvc.perform(
				post(URL_SALIDA).contentType(MediaType.APPLICATION_JSON).content(asJsonString(motoDTO)))
				.andExpect(status().isOk()); 
 

	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);

	}

}
