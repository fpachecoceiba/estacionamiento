package com.co.ceiba.parqueadero.builder;



import com.co.ceiba.parqueadero.dominio.CarroDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.Carro;

public final class CarroBuilder {

	private CarroBuilder() {

	}

	public static CarroDTO getDTO(Carro carro) {
		CarroDTO carroDTO = null;
		if (carro != null) {
			carroDTO = new CarroDTO(carro.getModelo(), carro.getPlaca(),
					carro.getTipoVehiculo());

		}
		return carroDTO;

	}

	public static Carro getEntidad(CarroDTO carroDTO) {
		Carro carro = null; 
		if (carroDTO != null) {
			carro = new Carro();
			carro.setPlaca(carroDTO.getPlaca());
			carro.setModelo(carroDTO.getModelo());
			carro.setTipoVehiculo(TipoVehiculo.CARRO.toString());
		}
		return carro;
	}

}
