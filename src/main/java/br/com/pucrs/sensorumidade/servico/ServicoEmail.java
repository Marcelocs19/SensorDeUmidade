package br.com.pucrs.sensorumidade.servico;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServicoEmail {

	private RestTemplate restTemplate;
	
	public ServicoEmail(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}
}
