package br.com.pucrs.sensorumidade.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.pucrs.sensorumidade.erro.ServicoErro;
import br.com.pucrs.sensorumidade.modelo.Leitura;
import br.com.pucrs.sensorumidade.repositorio.LeituraRepositorio;

@Service
public class LeituraServico {

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_SALVAR_LEITURA = "Erro ao salvar a leitura.";
	private static final String ERRO_BUSCAR_LEITURA = "Erro ao buscar a leitura.";
	private static final String ERRO_BUSCAR_LEITURA_NAO_ENCONTRADA = "Erro leitura não encontrada.";	
	private static final String ERRO_BUSCAR_ID = "Erro ao buscar o usuario pelo id.";

	private final LeituraRepositorio leituraRepositorio;

	public LeituraServico(LeituraRepositorio leituraRepositorio) {
		super();
		this.leituraRepositorio = leituraRepositorio;
	}

	public List<Leitura> listarLeituras() {
		try {
			List<Leitura> listaLeitura;
			listaLeitura = leituraRepositorio.findAll();
			if (!listaLeitura.isEmpty()) {
				return listaLeitura;
			} else {
				throw new ServicoErro(ERRO_BUSCAR_LEITURA_NAO_ENCONTRADA);
			}
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_BUSCAR_LEITURA, excecao);
		}
	}

	public Leitura buscarPorId(Long id) {
		try {
			Optional<Leitura> leitura = leituraRepositorio.findById(id);
			if (leitura.isPresent()) {
				return leitura.get();
			} else {
				throw new ServicoErro(ERRO_BUSCAR_LEITURA_NAO_ENCONTRADA);
			}
		} catch (IllegalArgumentException excecao) {
			throw new ServicoErro(ERRO_BUSCAR_ID, excecao);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_BUSCAR_ID);
		}
	}
	
	public void salvar(Leitura leitura) {
		try {
			this.leituraRepositorio.saveAndFlush(leitura);
		} catch (Exception excecao) {
			throw new ServicoErro(ERRO_SALVAR_LEITURA, excecao);
		}
	}

}
