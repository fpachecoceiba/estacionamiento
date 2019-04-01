package com.co.ceiba.parqueadero.dominio;

public class VehiculoDTO {

	private Long idVehiculo;
	private String placa;
	private String tipoVehiculo;

	public VehiculoDTO() {
		super();
	}

	public VehiculoDTO(Long idVehiculo, String placa, String tipoVehiculo) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
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
