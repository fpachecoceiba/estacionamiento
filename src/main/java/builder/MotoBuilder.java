package builder;

import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.entidad.Moto;

public final class MotoBuilder {

	private MotoBuilder() {

	}

	public static MotoDTO getMotoDTO(Moto moto) {
		MotoDTO motoDTO = null;
		if (moto != null) {
			motoDTO = new MotoDTO(moto.getCilindraje(), moto.getIdVehiculo(), moto.getPlaca(), moto.getTipoVehiculo());
		}
		return motoDTO;

	}

	public static Moto getEntidad(MotoDTO motoDTO) {
		Moto moto = null;
		if (motoDTO != null) {
			moto = new Moto();
			moto.setIdVehiculo(motoDTO.getIdVehiculo());
			moto.setPlaca(motoDTO.getPlaca());
			moto.setCilindraje(motoDTO.getCilindraje());
			moto.setTipoVehiculo(TipoVehiculo.MOTO.toString());
		}
		return moto;
	}

}
