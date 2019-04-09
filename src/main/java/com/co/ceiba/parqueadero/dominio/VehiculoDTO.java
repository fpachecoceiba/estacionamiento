package com.co.ceiba.parqueadero.dominio;

public class VehiculoDTO {
	private String placa;
	private String tipoVehiculo;

	public VehiculoDTO() {
		super();
	}

	public VehiculoDTO(String placa, String tipoVehiculo) {
		super();
		
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
