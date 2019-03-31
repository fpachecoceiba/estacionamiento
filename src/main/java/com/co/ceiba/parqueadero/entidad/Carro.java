package com.co.ceiba.parqueadero.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro {

	@Id
	@Column(name = "id_carro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarro;

	@Column(name = "placa", nullable = false)
	private String placa;

	@Column(name = "modelo", nullable = false)
	private String modelo;

	public Carro() {
		super();

	}

	public Carro(Long idCarro, String placa, String modelo) {
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
