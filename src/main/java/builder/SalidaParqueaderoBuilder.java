package builder;

import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;

public final class SalidaParqueaderoBuilder {

	private SalidaParqueaderoBuilder() {

	}

	public static SalidaParqueadero getSalidaParqueaderoEntidad(SalidaParqueaderoDTO salidaParqueaderoDTO) {
		return new SalidaParqueadero(salidaParqueaderoDTO.getIdSalida(), salidaParqueaderoDTO.getFechaSalida(),
				EntradaParqueaderoBuilder.getEntidad(salidaParqueaderoDTO.getEntradaParqueo()),
				salidaParqueaderoDTO.getValor()); 
	}

	public static SalidaParqueaderoDTO getSalidaParqueaderoDTO(SalidaParqueadero salidaParqueadero) {
		return new SalidaParqueaderoDTO(salidaParqueadero.getIdSalida(), salidaParqueadero.getFechaSalida(),
				EntradaParqueaderoBuilder.getDTO(salidaParqueadero.getEntradaParqueo()), salidaParqueadero.getValor());
	}
}
