package com.co.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EntradaParqueoDTO {
	private Long idEntrada;
	private LocalDateTime fechaEntrada;
	private Boolean activo;
	private String tipoVehiculo;

	public EntradaParqueoDTO() {
		super();
	}

	public EntradaParqueoDTO(Long idEntrada, LocalDateTime fechaEntrada, Boolean activo, String tipoVehiculo) {
		super();
		this.idEntrada = idEntrada;
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
		this.tipoVehiculo = tipoVehiculo;
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

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
