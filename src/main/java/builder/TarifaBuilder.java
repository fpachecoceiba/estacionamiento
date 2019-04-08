package builder;

import com.co.ceiba.parqueadero.dominio.TarifaDTO;
import com.co.ceiba.parqueadero.entidad.Tarifa;

public class TarifaBuilder {
	
	public static TarifaDTO getTarifaDTO(Tarifa tarifa) {

		return new TarifaDTO(tarifa.getModalidad(), tarifa.getValor(), tarifa.getTipoVehiculo());
	}
 


	

}
