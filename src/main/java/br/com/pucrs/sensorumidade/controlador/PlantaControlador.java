package br.com.pucrs.sensorumidade.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import br.com.pucrs.sensorumidade.modelo.Planta;
import br.com.pucrs.sensorumidade.servico.PlantaServico;

@Controller
@RequestMapping("/plantas")
public class PlantaControlador {

	/**
	 * Mapeamentos e Views
	 */
	private static final String VISUALIZAR_BUSCAR_PLANTA = "plantas/listarPlanta";
	private static final String VISUALIZAR_DETALHAR_PLANTA = "plantas/detalharPlanta";
	private static final String REDIRECIONAR_PLANTA = "redirect:/plantas";
	private static final String DETALHAR_PLANTA = "/{id}/detalhar";
	private static final String PLANTA = "planta";

	private static final String MODELO_PLANTA = "plantas";

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_BUSCAR_PLANTA = "Erro ao buscar a(s) planta(s).";

	/**
	 * Mensagens de erro para o model
	 */
	private static final String ERRO_MENSAGEM_PLANTA = "mensagemError";

	private final PlantaServico plantaServico;

	public PlantaControlador(PlantaServico plantaServico) {
		super();
		this.plantaServico = plantaServico;
	}

	@GetMapping()
	public ModelAndView buscarTodasPlantas(Planta planta, RedirectAttributes atribRedirecionamento) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_BUSCAR_PLANTA);
			List<Planta> listarPlanta = plantaServico.listarPlantas();
			modelo.addObject(MODELO_PLANTA, listarPlanta);
			return modelo;
		} catch (Exception excecao) {
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_PLANTA, ERRO_BUSCAR_PLANTA);
			return new ModelAndView(VISUALIZAR_BUSCAR_PLANTA);
		}
	}

	@GetMapping(DETALHAR_PLANTA)
	public ModelAndView detalharPlanta(@PathVariable("id") Long id) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_DETALHAR_PLANTA);
			Planta planta = plantaServico.buscarPorId(id);
			modelo.addObject(PLANTA, planta);
			return modelo;
		} catch (TemplateInputException excecaoTemplate) {
			return new ModelAndView(VISUALIZAR_BUSCAR_PLANTA);
		} catch (Exception excecao) {
			return new ModelAndView(REDIRECIONAR_PLANTA);
		}
	}

}
