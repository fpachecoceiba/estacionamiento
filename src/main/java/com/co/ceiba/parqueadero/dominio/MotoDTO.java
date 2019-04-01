package com.co.ceiba.parqueadero.dominio;

public class MotoDTO {
	private Long idMoto;
	private String placa;
	private Double cilindraje;

	public MotoDTO() {
		super();
	}

	public MotoDTO(Long idMoto, String placa, Double cilindraje) {
		super();
		this.idMoto = idMoto;
		this.placa = placa;
		this.cilindraje = cilindraje;
	}

	public Long getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(Long idMoto) {
		this.idMoto = idMoto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Double cilindraje) {
		this.cilindraje = cilindraje;
	}

}
