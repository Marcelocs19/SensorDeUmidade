package br.com.pucrs.sensorumidade.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import br.com.pucrs.sensorumidade.modelo.Leitura;
import br.com.pucrs.sensorumidade.servico.LeituraServico;

@Controller
@RequestMapping("/leituras")
public class LeituraControlador {

	/**
	 * Mapeamentos e Views
	 */
	private static final String VISUALIZAR_BUSCAR_LEITURA = "leituras/buscarLeitura";
	private static final String VISUALIZAR_DETALHAR_LEITURA = "leituras/leituraDetalhar";
	private static final String REDIRECIONAR_LEITURA = "redirect:/leituras";
	private static final String DETALHAR_LEITURA = "/{id}/detalhar";

	/**
	 * Mensagens para o model
	 */
	private static final String MODELO_LEITURA = "leituras";

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_BUSCAR_LEITURA = "Erro ao buscar a(s) leitura(s).";

	/**
	 * Mensagens de erro para o model
	 */
	private static final String ERRO_MENSAGEM_USUARIO = "mensagemError";

	private final LeituraServico leituraServico;

	public LeituraControlador(LeituraServico leituraServico) {
		super();
		this.leituraServico = leituraServico;
	}

	@GetMapping
	public ModelAndView buscarTodosLeitores(Leitura leitor, RedirectAttributes atribRedirecionamento) {
		try {
			ModelAndView modelo = new ModelAndView();
			List<Leitura> listarLeitores = leituraServico.listarLeituras();
			modelo.addObject(MODELO_LEITURA, listarLeitores);
			return modelo;
		} catch (Exception excecao) {
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_BUSCAR_LEITURA);
			return new ModelAndView(VISUALIZAR_BUSCAR_LEITURA);
		}
	}

	@GetMapping(DETALHAR_LEITURA)
	public ModelAndView detalharLeitura(@PathVariable("id") Long id) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_DETALHAR_LEITURA);
			modelo.addObject(this.leituraServico.buscarPorId(id));
			return modelo;
		} catch (TemplateInputException excecaoTemplate) {
			return new ModelAndView(VISUALIZAR_BUSCAR_LEITURA);
		} catch (Exception excecao) {
			return new ModelAndView(REDIRECIONAR_LEITURA);
		}
	}

}
