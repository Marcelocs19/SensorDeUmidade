package br.com.pucrs.sensorumidade.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pucrs.sensorumidade.modelo.Leitura;
import br.com.pucrs.sensorumidade.modelo.Sensor;
import br.com.pucrs.sensorumidade.servico.LeituraServico;
import br.com.pucrs.sensorumidade.servico.SensorServico;


@RestController
@RequestMapping("/servico")
public class SensorRestControlador {

	/**
	 * Mensagens para o email
	 */
	private static final String PLANTA_FORA_DO_INTERVALO = "A umidade da planta está fora do intervalo ideal.";
	private static final String PLANTA_DENTRO_DO_INTERVALO = "A umidade da planta está dentro do intervalo ideal.";
	
	@Autowired
	private SensorServico sensorServico;
	
	@Autowired
	private LeituraServico leituraServico;
	
	@Autowired
	private EmailControlador email; 
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Leitura> adicionarUsuario(@RequestBody @Valid Leitura leitura, BindingResult resultado,
			UriComponentsBuilder ucBuilder) {
		HttpHeaders cabecalho = new HttpHeaders();
		
		Sensor sensor = sensorServico.buscarPorId(leitura.getSensor().getId());

		leitura.setSensor(sensor);		

		if (resultado.hasErrors() || (leitura == null)) {
			return new ResponseEntity<Leitura>(cabecalho, HttpStatus.BAD_REQUEST);
		}
		
		leituraServico.salvar(leitura);
		
		cabecalho.setLocation(ucBuilder.path("/servico/{id}").buildAndExpand(leitura.getId()).toUri());
		
		Sensor sensorAtual = sensorServico.buscarPorId(leitura.getSensor().getId());
				
		if (leitura.getUmidadeAtual() < sensorAtual.getPlanta().getTipo().getUmidade_minima()
				|| leitura.getUmidadeAtual() > sensorAtual.getPlanta().getTipo().getUmidade_maxima()) {
			sensorAtual.setMensagem(PLANTA_FORA_DO_INTERVALO);
			email.setLeitura(leitura);
			email.sendMail();
		} else {
			sensorAtual.setMensagem(PLANTA_DENTRO_DO_INTERVALO);
		}
		return new ResponseEntity<Leitura>(leitura, cabecalho, HttpStatus.CREATED); 
	}
	
}
