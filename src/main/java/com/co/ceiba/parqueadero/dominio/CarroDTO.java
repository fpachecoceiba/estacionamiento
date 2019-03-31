package com.co.ceiba.parqueadero.dominio;

public class CarroDTO {
	private Long idCarro;
	private String placa;
	private String modelo;

	public CarroDTO() {
		super();
	}

	public CarroDTO(Long idCarro, String placa, String modelo) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.idCarro = idCarro;

	}

	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
