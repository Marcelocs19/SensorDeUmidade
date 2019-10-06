package br.com.pucrs.sensorumidade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.pucrs.sensorumidade.servico.ServicoEmail;

@SpringBootApplication
public class SensorDeUmidadeApplication {

	private static final Logger log = LoggerFactory.getLogger(SensorDeUmidadeApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SensorDeUmidadeApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		log.info(" *** **** Criando um template Rest");
		RestTemplateBuilder builder = new RestTemplateBuilder();
		return builder.build();
	}

	@Bean
	public ServicoEmail sensorService(RestTemplate restTemplate) {
		log.info(" *** **** Criando um serviço Sensor");
		return new ServicoEmail(restTemplate);
	}
	
}
