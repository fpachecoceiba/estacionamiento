package com.co.ceiba.parqueadero.entidad;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="salida_parqueadero")
@Table(name = "salida_parqueadero")
public class SalidaParqueadero {

	@Id
	@Column(name = "id_salida")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSalida;
	
	@Column(name = "fechaSalida", nullable = false)
	private LocalDateTime fechaSalida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_entrada_parqueo")
	private EntradaParqueo entradaParqueo;

	@Column(name = "valor", nullable = false)
	private Double valor;

	public SalidaParqueadero() {
		super();

	}

	public SalidaParqueadero(Long idSalida, LocalDateTime fechaSalida, EntradaParqueo entradaParqueo, Double valor) {
		super();
		this.idSalida = idSalida;
		this.fechaSalida = fechaSalida;
		this.entradaParqueo = entradaParqueo;
		this.valor = valor;
	}

	public Long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(Long idSalida) {
		this.idSalida = idSalida;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public EntradaParqueo getEntradaParqueo() {
		return entradaParqueo;
	}

	public void setEntradaParqueo(EntradaParqueo entradaParqueo) {
		this.entradaParqueo = entradaParqueo; 
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
