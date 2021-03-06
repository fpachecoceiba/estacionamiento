package com.co.ceiba.parqueadero.entidad;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "entrada_parqueo")
@Table(name = "entrada_parqueo")
public class EntradaParqueo {

	@Id
	@Column(name = "id_entrada")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEntrada;

	@Column(name = "fecha_entrada", nullable = false)
	private Timestamp fechaEntrada;

	@Column(name = "activo", nullable = false)
	private Boolean activo;

	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;
 
	@ManyToOne
	@JoinColumn(name = "placa")
	private Vehiculo vehiculo;

	public EntradaParqueo() {
		super();
	} 

	public EntradaParqueo(Long idEntrada, Timestamp fechaEntrada, Boolean activo, String tipoVehiculo,Vehiculo vehiculo) {
		super();
		this.idEntrada = idEntrada;
		this.fechaEntrada = fechaEntrada;
		this.activo = activo;
		this.tipoVehiculo = tipoVehiculo;
		this.vehiculo = vehiculo;
	}

	public Long getIdEntrada() {
		return idEntrada;
	}



	public Timestamp getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Timestamp fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setPlaca(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
