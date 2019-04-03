package com.co.ceiba.parqueadero.servicio.servicioimpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.ceiba.parqueadero.dominio.EntradaParqueoDTO;
import com.co.ceiba.parqueadero.dominio.MotoDTO;
import com.co.ceiba.parqueadero.dominio.SalidaParqueaderoDTO;
import com.co.ceiba.parqueadero.dominio.VehiculoDTO;
import com.co.ceiba.parqueadero.entidad.SalidaParqueadero;
import com.co.ceiba.parqueadero.entidad.Vehiculo;
import com.co.ceiba.parqueadero.repositorio.EntradaParqueoRepository;
import com.co.ceiba.parqueadero.repositorio.SalidaParqueaderoRepository;
import com.co.ceiba.parqueadero.repositorio.VehiculoRepository;
import com.co.ceiba.parqueadero.servicio.SalidaParqueaderoService;
import com.co.ceiba.parqueadero.servicio.TarifaService;
import com.co.ceiba.parqueadero.servicio.reglas.CalcularTarifaSalida;

@Service
public class SalidaParqueaderoServiceImpl implements SalidaParqueaderoService {
	private LocalDateTime fechaSalida = LocalDateTime.now();

	@Autowired
	private SalidaParqueaderoRepository salidaParqueaderoRepository;
	@Autowired
	private VehiculoRepository vehiculoRepository;
	@Autowired
	private EntradaParqueoRepository entradaParqueoRepository;
	@Autowired
	private TarifaService tarifaService;

	@Override
	public SalidaParqueaderoDTO guardar(VehiculoDTO vehiculoDTO) {
		Double cilindraje = 0.0;
		if (vehiculoDTO instanceof MotoDTO) {
			MotoDTO motoDTO = (MotoDTO) vehiculoDTO;
			cilindraje = motoDTO.getCilindraje();
		}
		Optional<Vehiculo> optVehiculo = vehiculoRepository.findById(vehiculoDTO.getIdVehiculo());
		if (optVehiculo.isPresent()) {
			String placa = optVehiculo.get().getPlaca();
			String tipoVehiculo = optVehiculo.get().getTipoVehiculo();
			EntradaParqueoDTO entradaParqueo = EntradaParqueoServiceImpl.getDTO(entradaParqueoRepository.consultarActivaPorId(placa));
			Double valorTotal = new CalcularTarifaSalida(tarifaService).getValorTarifa(tipoVehiculo, cilindraje);
			SalidaParqueaderoDTO salidaParqueaderoDTO = new SalidaParqueaderoDTO();
			salidaParqueaderoDTO.setEntradaParqueo(entradaParqueo);
			salidaParqueaderoDTO.setFechaSalida(fechaSalida);
			salidaParqueaderoDTO.setValor(valorTotal);
			SalidaParqueadero salidaParqueadero = getSalidaParqueaderoEntidad(salidaParqueaderoDTO);
			salidaParqueaderoRepository.save(salidaParqueadero);

			return getSalidaParqueaderoDTO(salidaParqueadero);
		}
		return null;
	}

	public static SalidaParqueadero getSalidaParqueaderoEntidad(SalidaParqueaderoDTO salidaParqueaderoDTO) {
		return new SalidaParqueadero(salidaParqueaderoDTO.getIdSalida(), salidaParqueaderoDTO.getFechaSalida(),
				EntradaParqueoServiceImpl.getEntidad(salidaParqueaderoDTO.getEntradaParqueo()),
				salidaParqueaderoDTO.getValor());
	}

	public static SalidaParqueaderoDTO getSalidaParqueaderoDTO(SalidaParqueadero salidaParqueadero) {
		return new SalidaParqueaderoDTO(salidaParqueadero.getIdSalida(), salidaParqueadero.getFechaSalida(),
				EntradaParqueoServiceImpl.getDTO(salidaParqueadero.getEntradaParqueo()), salidaParqueadero.getValor());
	}

}
