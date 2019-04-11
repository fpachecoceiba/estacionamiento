package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name= "carro")
@Table(name = "carro")
public class Carro extends Vehiculo {

	@Column(name = "modelo")
	private String modelo;
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}




}
