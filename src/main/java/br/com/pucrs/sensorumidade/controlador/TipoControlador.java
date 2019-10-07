package br.com.pucrs.sensorumidade.controlador;

import org.springframework.stereotype.Controller;

import br.com.pucrs.sensorumidade.servico.TipoServico;

//@Controller
public class TipoControlador {
	
	private final TipoServico tipoServico;
	
	public TipoControlador(TipoServico tipoServico) {
		super();
		this.tipoServico = tipoServico;
	}

//	@GetMapping
//	public ModelAndView buscarTodosTipos() {
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
}
