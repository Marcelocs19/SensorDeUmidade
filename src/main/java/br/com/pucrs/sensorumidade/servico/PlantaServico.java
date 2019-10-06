package br.com.pucrs.sensorumidade.servico;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pucrs.sensorumidade.erro.ServicoErro;
import br.com.pucrs.sensorumidade.modelo.Planta;
import br.com.pucrs.sensorumidade.repositorio.PlantaRepositorio;

@Service
public class PlantaServico {

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_BUSCAR_PLANTA = "Erro ao buscar a(s) planta(s).";
	private static final String ERRO_BUSCAR_PLANTA_NAO_ENCONTRADA = "Erro planta(s) não encontrada(s).";
	private static final String ERRO_DETALAHAR_PLANTA = "Erro ao detalhar planta.";
	private static final String ERRO_DETALAHAR_POR_ID_PLANTA = "Erro ao detalhar planta pelo id.";

	private final PlantaRepositorio plantaRepositorio;

	public PlantaServico(PlantaRepositorio plantaRepositorio) {
		super();
		this.plantaRepositorio = plantaRepositorio;
	}

	public List<Planta> listarPlantas() {
		try {
			Optional<Planta> listarPlanta;
			listarPlanta = plantaRepositorio.findAllOrderByNomeAsc();
			if (listarPlanta.isPresent()) {
				return Arrays.asList(listarPlanta.get());
			} else {
				throw new ServicoErro(ERRO_BUSCAR_PLANTA_NAO_ENCONTRADA);
			}

		} catch (IllegalArgumentException excecao) {
			throw new ServicoErro(ERRO_BUSCAR_PLANTA, excecao);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_BUSCAR_PLANTA, excecao);
		}
	}

	public Planta buscarPorId(Long id) {
		try {
			Optional<Planta> planta = plantaRepositorio.findById(id);
			if (planta.isPresent()) {
				return planta.get();
			} else {
				throw new ServicoErro(ERRO_DETALAHAR_PLANTA);
			}
		} catch (IllegalArgumentException excecao) {
			throw new ServicoErro(ERRO_DETALAHAR_POR_ID_PLANTA, excecao);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_DETALAHAR_POR_ID_PLANTA);
		}
	}

}
