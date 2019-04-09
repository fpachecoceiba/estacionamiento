package com.co.ceiba.parqueadero.dominio;

public class MotoDTO extends VehiculoDTO {
	private Double cilindraje;

	public MotoDTO() {
		super();
	}

	public MotoDTO(Double cilindraje, String placa,String tipoVehiculo) {
		super(placa,tipoVehiculo);

		this.cilindraje = cilindraje;
	}

	

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
