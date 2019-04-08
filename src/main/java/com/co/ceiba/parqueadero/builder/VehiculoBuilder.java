package com.co.ceiba.parqueadero.builder;



import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.Vehiculo;

public final class VehiculoBuilder {
	
	private VehiculoBuilder() {
		
	}
	public static Vehiculo getVehiculoEntidad(VehiculoDTO vehiculoDTO) {
		return new Vehiculo(vehiculoDTO.getIdVehiculo(), vehiculoDTO.getPlaca(), vehiculoDTO.getTipoVehiculo());
	}

	public static VehiculoDTO getVehiculoDTO(Vehiculo vehiculo) {
		return new VehiculoDTO(vehiculo.getIdVehiculo(), vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
	}

}
 