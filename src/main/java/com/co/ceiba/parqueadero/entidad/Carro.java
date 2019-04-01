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
@Table(name = "carro")
public class Carro {

	@Id
	@Column(name = "id_carro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarro;

	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo idVehiculo;

	public Carro() {
	}

	public Carro(Long idCarro, String modelo) {
		
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
	
	public Vehiculo getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Vehiculo idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	

	

}
