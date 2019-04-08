package com.co.ceiba.parqueadero.builder;

import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Vehiculo;

public final class VehiculoBuilder {

	private VehiculoBuilder() {

	}

	public static Vehiculo getVehiculoEntidad(VehiculoDTO vehiculoDTO) {
		Vehiculo vehiculo = null;
		if (vehiculoDTO != null) {
			vehiculo = new Vehiculo();
			vehiculo.setIdVehiculo(vehiculoDTO.getIdVehiculo());
			vehiculo.setPlaca(vehiculoDTO.getPlaca());
			vehiculo.setTipoVehiculo(vehiculoDTO.getTipoVehiculo());
		}

		return vehiculo;
	}

	public static VehiculoDTO getVehiculoDTO(Vehiculo vehiculo) {
		VehiculoDTO vehiculoDTO = null;
		if (vehiculo != null) {
			vehiculoDTO = new VehiculoDTO();
			vehiculoDTO.setIdVehiculo(vehiculo.getIdVehiculo());
			vehiculoDTO.setPlaca(vehiculo.getPlaca());
			vehiculoDTO.setTipoVehiculo(vehiculo.getTipoVehiculo());
		}
		return vehiculoDTO;
	}

}
