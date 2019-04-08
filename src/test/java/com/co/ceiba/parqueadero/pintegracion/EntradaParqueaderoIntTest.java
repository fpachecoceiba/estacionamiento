package com.co.ceiba.parqueadero.pintegracion;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaParqueaderoIntTest {


	private static final String URL_CARRO = "http://localhost:8080/apiv1/vehiculo/carro";
   private WebApplicationContext webApplicationContext;
 
	   private MockMvc mockMvc;

	   @Before
	   public void setup() {
	       mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	   
	  // @Test
	   public void vehiculos() throws Exception {
		   CarroDTO user = new CarroDTO("MODELO", 1L, "00002", TipoVehiculo.CARRO.toString());
		   MotoDTO motoDTO = new MotoDTO(550.0, 1l, "5455", TipoVehiculo.MOTO.toString());
		    mockMvc.perform(
		            post(URL_CARRO)
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(asJsonString(user)))
		            .andExpect(status().isCreated())
		            .andExpect(header().string("location", containsString(URL_CARRO)));
		   
	   }
	
	   public static String asJsonString(final Object obj) {
		    try {
		        final ObjectMapper mapper = new ObjectMapper();
		        final String jsonContent = mapper.writeValueAsString(obj);
		        return jsonContent;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
		} 


}