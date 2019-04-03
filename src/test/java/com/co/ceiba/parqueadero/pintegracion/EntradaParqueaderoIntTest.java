package com.co.ceiba.parqueadero.pintegracion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.ceiba.parqueadero.dominio.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntradaParqueaderoIntTest {

	@LocalServerPort
	private int puerto;
	private static final String LOCALHOST_URL = "http://localhost:";

	private static final String URL_CARRO = "/apiv1/vehiculos/carro";

	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders httpHeaders = new HttpHeaders();

	@Test
	public void registrarVehiculoTest() {
		CarroDTO carroDTO = new CarroDTO(1l, "2017", 1l, "AAA0123", "CARRO");
		HttpEntity<CarroDTO> entity = new HttpEntity<>(carroDTO, httpHeaders);
		ResponseEntity<String> response = testRestTemplate.exchange(getUrl(URL_CARRO), HttpMethod.POST,
				entity, String.class);
		HttpStatus codigo = response.getStatusCode();
		assertEquals(HttpStatus.OK, codigo);

	}
	
	private String getUrl(String uri) {
		return LOCALHOST_URL + puerto + uri;
	}

}