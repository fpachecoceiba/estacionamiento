package com.co.ceiba.parqueadero.dominio;

public class TarifaDTO {
	private String modalidad;
	private Double valor;
	private String tipoVehiculo;

	public TarifaDTO() {
		super(); 
	}

	public TarifaDTO(String modalidad, Double valor, String tipoVehiculo) {
		super();
		this.modalidad = modalidad;
		this.valor = valor;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
