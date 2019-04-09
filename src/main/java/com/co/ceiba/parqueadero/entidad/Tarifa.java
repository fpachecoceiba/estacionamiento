package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarifa")
public class Tarifa {
	@Id
	@Column(name = "id_tarifa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarifa;
	
	@Column(name = "modalidad", nullable = false)
	private String modalidad;

	@Column(name = "valor", nullable = false)
	private Double valor;

	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;

	public Tarifa() {
		super();
	}



	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}
