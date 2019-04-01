package com.co.ceiba.parqueadero.dominio;

public class MotoDTO extends VehiculoDTO {
	private Long idMoto;
	private Double cilindraje;

	public MotoDTO() {
		super();
	}

	public MotoDTO(Long idMoto, Double cilindraje, Long idVehiculo, String placa,String tipoVehiculo) {
		super(idVehiculo, placa,tipoVehiculo);
		this.idMoto = idMoto;
		this.cilindraje = cilindraje;
	}

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
