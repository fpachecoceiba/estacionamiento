package com.co.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class EntradaParqueoDTO {
	private Long idEntrada;
	LocalDateTime fechaEntrada;
	private Boolean activo;
	private Vehiculo vehiculo;

	public EntradaParqueoDTO() {
		super();
	} 

	public EntradaParqueoDTO(Long idEntrada, LocalDateTime fechaEntrada, Boolean activo,Vehiculo vehiculo) {
		super();
		this.idEntrada = idEntrada;
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
		this.vehiculo = vehiculo; 
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
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setPlaca(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
}
