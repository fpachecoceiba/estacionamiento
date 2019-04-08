package com.co.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SalidaParqueaderoDTO {
	private Long idSalida;
	private LocalDateTime fechaSalida;
	private Double valor;
	private EntradaParqueoDTO entradaParqueo;

	public SalidaParqueaderoDTO() {
		super();
	}

	public SalidaParqueaderoDTO(Long idSalida, LocalDateTime fechaSalida, EntradaParqueoDTO entradaParqueo,
			Double valor) {
		super();
		this.idSalida = idSalida;
		this.fechaSalida = fechaSalida;
		this.entradaParqueo = entradaParqueo; 
		this.valor = valor;
	}

	public Long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Long idSalida) {
		this.idSalida = idSalida;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public EntradaParqueoDTO getEntradaParqueo() {
		return entradaParqueo;
	}

	public void setEntradaParqueo(EntradaParqueoDTO entradaParqueo) {
		this.entradaParqueo = entradaParqueo;
	}
 
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
