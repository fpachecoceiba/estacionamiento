package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "moto")
public class Moto {

	@Id
	@Column(name = "id_moto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMoto;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo idVehiculo;
	
	@Column(name = "cilindraje", nullable = false)
	private Double cilindraje;

	public Moto() {
		super();
	}

	public Moto(Long idMoto, Double cilindraje) {
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
	
	public Vehiculo getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Vehiculo idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

}
