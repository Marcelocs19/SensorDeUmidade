package br.com.pucrs.sensorumidade.controlador;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

import br.com.pucrs.sensorumidade.modelo.Sensor;
import br.com.pucrs.sensorumidade.servico.SensorServico;

@Controller
@RequestMapping("/sensores")
public class SensorControlador {

	/**
	 * Mapeamentos e Views
	 */
	private static final String REDIRECIONAR_NOVO_SENSOR = "redirect:/sensores/novo";
	private static final String REDIRECIONAR_SENSOR = "redirect:/sensores";
	private static final String VISUALIZAR_NOVO_SENSOR = "sensores/novoSensor";
	private static final String VISUALIZAR_BUSCAR_SENSOR = "sensores/buscarSensor";
	private static final String VISUALIZAR_DETALHAR_SENSOR = "sensores/sensorDetalhar";
	private static final String DETALHAR_SENSOR = "/{id}/detalhar";
	private static final String NOVO_SENSOR = "/novo";

	/**
	 * Mensagens para o model
	 */
	private static final String MENSAGEM = "mensagem";
	private static final String MODELO_SENSOR = "sensores";

	/**
	 * Mensagens de sucesso
	 */
	private static final String SALVAR_SENSOR_SUCESSO = "Sensor salvo com sucesso.";

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_BUSCAR_SENSOR = "Erro ao buscar o(s) sensor(es).";
	private static final String ERRO_SALVAR_SENSOR = "Erro ao salvar o sensor.";

	/**
	 * Mensagens de erro para o model
	 */
	private static final String ERRO_MENSAGEM_SENSOR = "mensagemError";

	private final SensorServico sensorServico;

	public SensorControlador(SensorServico sensorServico) {
		super();
		this.sensorServico = sensorServico;
	}

	@GetMapping
	public ModelAndView buscarTodosSensores(RedirectAttributes atribRedirecionamento) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_BUSCAR_SENSOR);
			List<Sensor> listarSensores = sensorServico.listarSensores();
			modelo.addObject(MODELO_SENSOR, listarSensores);
			return modelo;
		} catch (Exception excecao) {
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_SENSOR, ERRO_BUSCAR_SENSOR);
			return new ModelAndView(VISUALIZAR_BUSCAR_SENSOR);
		}
	}

	@GetMapping(NOVO_SENSOR)
	public ModelAndView visualizarNovoSensor(Sensor sensor) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_NOVO_SENSOR);
			modelo.addObject(MODELO_SENSOR, sensor);
			return modelo;
		} catch (Exception excecao) {
			ModelAndView erro = new ModelAndView(VISUALIZAR_NOVO_SENSOR);
			erro.addObject(MODELO_SENSOR, sensor);
			return erro;
		}
	}

	@PostMapping(NOVO_SENSOR)
	@Transactional
	public ModelAndView criarNovoSensor(@Valid Sensor sensor, BindingResult resultado,
			RedirectAttributes atribRedirecionamento) {
		try {
			if (sensorServico.validarSalvar(sensor, resultado)) {
				return visualizarNovoSensor(sensor);
			} else {
				ModelAndView modelo = new ModelAndView(REDIRECIONAR_NOVO_SENSOR);
				sensorServico.salvar(sensor);
				atribRedirecionamento.addFlashAttribute(MENSAGEM, SALVAR_SENSOR_SUCESSO);
				return modelo;
			}
		} catch (Exception excecao) {
			ModelAndView erro = new ModelAndView(REDIRECIONAR_NOVO_SENSOR);
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_SENSOR, ERRO_SALVAR_SENSOR);
			return erro;
		}

	}

	@GetMapping(DETALHAR_SENSOR)
	public ModelAndView detalharUsuario(@PathVariable("id") Long id) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_DETALHAR_SENSOR);
			modelo.addObject(this.sensorServico.buscarPorId(id));
			return modelo;
		} catch (TemplateInputException excecaoTemplate) {
			return new ModelAndView(VISUALIZAR_BUSCAR_SENSOR);
		}catch (Exception excecao) {
			return new ModelAndView(REDIRECIONAR_SENSOR);
		}
	}

}
