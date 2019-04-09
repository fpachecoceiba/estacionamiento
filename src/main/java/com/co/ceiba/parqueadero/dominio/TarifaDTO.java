package com.co.ceiba.parqueadero.dominio;

public class TarifaDTO {
	private String modalidad;
	private Double valor;
	private String tipoVehiculo;


	public TarifaDTO(String modalidad, Double valor, String tipoVehiculo) {
		super();
		this.modalidad = modalidad;
		this.valor = valor;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public Double getValor() {
		return valor;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}



}
