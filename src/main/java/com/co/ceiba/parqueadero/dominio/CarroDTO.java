package com.co.ceiba.parqueadero.dominio;

public class CarroDTO extends VehiculoDTO {
	private Long idCarro;
	private String modelo;

	public CarroDTO() {
		super();
	}

	public CarroDTO(Long idCarro, String modelo,Long idVehiculo,String placa,String tipoVehiculo) {
		super(idVehiculo, placa,tipoVehiculo);
		this.modelo = modelo;
		this.idCarro = idCarro;

	}

	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	


}
