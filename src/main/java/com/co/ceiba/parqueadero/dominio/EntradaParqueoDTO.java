package com.co.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EntradaParqueoDTO {
	private Long idEntrada;
	LocalDateTime fechaEntrada;
	private Boolean activo;
	private Vehiculo idVehiculo;

	public EntradaParqueoDTO() {
		super();
	} 

	public EntradaParqueoDTO(Long idEntrada, LocalDateTime fechaEntrada, Boolean activo,Vehiculo idVehiculo) {
		super();
		this.idEntrada = idEntrada;
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
		this.idVehiculo = idVehiculo; 
	}
 
	public Long getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Long idEntrada) {
		this.idEntrada = idEntrada;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public Vehiculo getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Vehiculo idVahiculo) {
		this.idVehiculo = idVahiculo;
	}
	
}
