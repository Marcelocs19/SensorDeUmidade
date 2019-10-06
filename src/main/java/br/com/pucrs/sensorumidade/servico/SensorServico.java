package br.com.pucrs.sensorumidade.servico;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.com.pucrs.sensorumidade.erro.ServicoErro;
import br.com.pucrs.sensorumidade.modelo.Sensor;
import br.com.pucrs.sensorumidade.repositorio.SensorRepositorio;

@Service
public class SensorServico {

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_BUSCAR_SENSOR = "Erro ao buscar o(s) sensor(es).";
	private static final String ERRO_BUSCAR_SENSOR_NAO_ENCONTRADO = "Erro sensor(es) não encontrado(s).";
	private static final String ERRO_SALVAR_SENSOR = "Erro ao salvar o sensor.";
	private static final String ERRO_BUSCAR_ID = "Erro ao buscar o usuario pelo id.";
	
	private final SensorRepositorio sensorRepositorio;

	public SensorServico(SensorRepositorio sensorRepositorio) {
		super();
		this.sensorRepositorio = sensorRepositorio;
	}

	public List<Sensor> listarSensores() {
		try {
			Optional<Sensor> listaSensor;
			listaSensor = sensorRepositorio.findAllOrderByNomeAsc();
			if (listaSensor.isPresent()) {
				return Arrays.asList(listaSensor.get());
			} else {
				throw new ServicoErro(ERRO_BUSCAR_SENSOR_NAO_ENCONTRADO);
			}
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_BUSCAR_SENSOR, excecao);
		}
	}

	public boolean validarSalvar(Sensor sensor, BindingResult resultado) {
		try {
			boolean validar = true;
			if (resultado.hasErrors()) {
				validar = false;
			}
			return validar;
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_SALVAR_SENSOR, excecao);
		}
	}

	public void salvar(Sensor sensor) {
		try {
			this.sensorRepositorio.saveAndFlush(sensor);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_SALVAR_SENSOR, excecao);
		}

	}

	public Sensor buscarPorId(Long id) {
		try {
			Optional<Sensor> sensor = sensorRepositorio.findById(id);
			if (sensor.isPresent()) {
				return sensor.get();
			} else {
				throw new ServicoErro(ERRO_BUSCAR_SENSOR_NAO_ENCONTRADO);
			}
		} catch (IllegalArgumentException excecao) {
			throw new ServicoErro(ERRO_BUSCAR_ID, excecao);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_BUSCAR_ID);
		}
	}

}
