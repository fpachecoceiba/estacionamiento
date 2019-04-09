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

import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.util.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaParqueaderoControllerTest {
	private static final String URL_ENTRADA = "/apiv1/entrada/registrar";
	private static final String PLACA_CARRO = "PCL002";
	private static final String PLACA_MOTO = "PCL001";
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	@Test
	public void registrar() throws  Exception {
		VehiculoDTO vehiculoDTO = new VehiculoDTO(PLACA_MOTO, TipoVehiculo.CARRO.toString());
		mockMvc.perform(post(URL_ENTRADA).contentType(MediaType.APPLICATION_JSON).content(UtilTest.asJsonString(vehiculoDTO)))
		.andExpect(status().isOk());
		
		
	}
}
