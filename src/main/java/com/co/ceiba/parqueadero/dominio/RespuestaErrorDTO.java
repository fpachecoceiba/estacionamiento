package com.co.ceiba.parqueadero.dominio;

import java.util.Date;

public class RespuestaErrorDTO {
	private Date fecha;
	private String mensaje;
	private String descripcion;

	public RespuestaErrorDTO() {
		super();
	}
	

	public RespuestaErrorDTO(Date fecha, String mensaje, String descripcion) {
		super();
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
