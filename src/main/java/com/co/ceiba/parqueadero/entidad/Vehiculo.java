package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "vehiculo", uniqueConstraints = { @UniqueConstraint(columnNames = { "placa" }) })
public class Vehiculo {
	@Id
	@Column(name = "id_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVehiculo;

	@Column(name = "placa", nullable = false)
	private String placa;

	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;

	public Vehiculo() {
		super();
	}

	public Vehiculo(Long idVehiculo, String placa) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;

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
