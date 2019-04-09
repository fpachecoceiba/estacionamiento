package com.co.ceiba.parqueadero.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class UtilTest {
	private UtilTest() {

	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);

	}

}
