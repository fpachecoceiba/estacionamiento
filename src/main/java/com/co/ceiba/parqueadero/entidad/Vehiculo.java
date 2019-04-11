package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "vehiculo")
@Table(name = "vehiculo")
public class Vehiculo {
	@Id
	@Column(name = "placa", nullable = false)
	private String placa;

	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;

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
