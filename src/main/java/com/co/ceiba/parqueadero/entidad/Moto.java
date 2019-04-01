package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "moto", uniqueConstraints = { @UniqueConstraint(columnNames = { "placa" }) })
public class Moto {

	@Id
	@Column(name = "id_moto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMoto;
	
	@Column(name = "placa", nullable = false)
	private String placa;
	
	@Column(name = "cilindraje", nullable = false)
	private Double cilindraje;

	public Moto() {
		super();
	}

	public Moto(Long idMoto, String placa, Double cilindraje) {
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
