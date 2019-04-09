package com.co.ceiba.parqueadero.dominio;

public class CarroDTO extends VehiculoDTO {
	private String modelo;

	public CarroDTO() {
		super();
	}

	public CarroDTO(String modelo,String placa, String tipoVehiculo) {
		super(placa, tipoVehiculo);
		this.modelo = modelo;

	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
