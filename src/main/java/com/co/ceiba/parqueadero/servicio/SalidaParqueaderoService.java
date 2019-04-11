package com.co.ceiba.parqueadero.servicio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.TipoVehiculo;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.dominio.excepcion.RegistroNoExisteException;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.MotoRepository;
import com.co.ceiba.parqueadero.repositorio.SalidaParqueaderoRepository;
import com.co.ceiba.parqueadero.repositorio.TarifaRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.reglas.CalcularTarifaSalida;

@Service
public class SalidaParqueaderoService {

	private static final String MENSAJE_NOEXISTE_VEHICULO = "No existe un vehiculo registrado para esta informacion";

	private LocalDateTime fechaSalida = LocalDateTime.now();
	private SalidaParqueaderoRepository salidaParqueaderoRepository;
	private VehiculoRepository vehiculoRepository;
	private EntradaParqueoRepository entradaParqueoRepository;
	private TarifaRepository tarifaRepository;
	private MotoRepository motoRepository;

	public SalidaParqueaderoService(SalidaParqueaderoRepository salidaParqueaderoRepository,
			EntradaParqueoRepository entradaParqueoRepository, VehiculoRepository vehiculoRepository,
			TarifaRepository tarifaRepository, MotoRepository motoRepository) {
		this.salidaParqueaderoRepository = salidaParqueaderoRepository;
		this.entradaParqueoRepository = entradaParqueoRepository;
		this.vehiculoRepository = vehiculoRepository;
		this.tarifaRepository = tarifaRepository;
		this.motoRepository = motoRepository;

	}

	public SalidaParqueaderoDTO registrar(VehiculoDTO vehiculoDTO) {

		String placa = vehiculoDTO.getPlaca();
		VehiculoDTO vehiculoDTO2 = vehiculoRepository.findByPlaca(placa);
		if (vehiculoDTO2 == null) {
			throw new RegistroNoExisteException(MENSAJE_NOEXISTE_VEHICULO);
		}
		String tipoVehiculo = vehiculoDTO2.getTipoVehiculo();
		Double cilindraje = 0.0;
		if (tipoVehiculo.equals(TipoVehiculo.MOTO.toString())) {
			MotoDTO motoDTO = motoRepository.findByPlaca(vehiculoDTO2.getPlaca());
			cilindraje = motoDTO.getCilindraje();
		}

		List<EntradaParqueoDTO> entradaParqueoDTO = entradaParqueoRepository.consultarActivaPorPlaca(placa);
		Double valor = new CalcularTarifaSalida(tarifaRepository).getValorTarifa(tipoVehiculo, cilindraje,
				entradaParqueoDTO.get(0).getFechaEntrada(), fechaSalida);
		entradaParqueoDTO.get(0).setActivo(false);
		SalidaParqueaderoDTO salidaParqueaderoDTO = new SalidaParqueaderoDTO();
		salidaParqueaderoDTO.setEntradaParqueo(entradaParqueoDTO.get(0));
		salidaParqueaderoDTO.setFechaSalida(fechaSalida);
		salidaParqueaderoDTO.setValor(valor);

		return salidaParqueaderoRepository.registrar(salidaParqueaderoDTO);

	}

	public List<SalidaParqueaderoDTO> consultar(String placa) {
		return salidaParqueaderoRepository.consultar(placa);
	}

}
