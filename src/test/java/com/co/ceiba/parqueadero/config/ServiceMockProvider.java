package com.co.ceiba.parqueadero.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;

@Profile("test")
@Configuration
public class ServiceMockProvider {

	@Bean
	public VehiculoRepository vehiculoRepository() {
		return Mockito.mock(VehiculoRepository.class);
	}

	@Bean
	public EntradaParqueoRepository entradaParqueoRepository() {
		return Mockito.mock(EntradaParqueoRepository.class);
	}
}
