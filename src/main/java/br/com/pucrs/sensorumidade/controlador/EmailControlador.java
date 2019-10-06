package br.com.pucrs.sensorumidade.controlador;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pucrs.sensorumidade.modelo.Leitura;

@RestController
public class EmailControlador {
	
	/**
	 * Mapeamentos e Views
	 */
	private static final String ENVIAR_EMAIL = "/enviar-email";
	
	/**
	 * Email
	 */
	private static final String EMAIL = "desafiodb2019@gmail.com";
	
	/**
	 * Mensagens de sucesso
	 */
	private static final String ENVIAR_EMAIL_SUCESSO = "Email enviado com sucesso!";
	
	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_ENVIAR_EMAIL = "Erro ao enviar o email.";
	
	@Autowired
	private JavaMailSender mailSender;

	private Leitura leitura;


	@PostMapping(ENVIAR_EMAIL)
	public String sendMail() {
		try {
			MimeMessage mensagem = mailSender.createMimeMessage();
			MimeMessageHelper ajuda = new MimeMessageHelper(mensagem);
			ajuda.setTo(EMAIL);
			ajuda.setSubject("Cuidado com a umidade da Planta: " + leitura.getSensor().getPlanta().getTipo().getNome());
			ajuda.setText("<p>O Sensor " + leitura.getSensor().getNome() + " id: " + leitura.getSensor().getId()
					+ " está indicando que a umidade da planta" + leitura.getSensor().getPlanta().getTipo().getNome()
					+ " se encontra fora do intervalo ideal.</p>" + "<p>Umidade Atual: " + leitura.getUmidadeAtual()  + "%" + "."
					+ "</p><p>Intervalo ideal de umidade: " + "umidade mínima: " + leitura.getSensor().getPlanta().getTipo().getUmidade_minima() + "%"
					+ " umidade máxima: " + leitura.getSensor().getPlanta().getTipo().getUmidade_maxima() + "%" + "." + "</p>", true);
			mailSender.send(mensagem);
			return ENVIAR_EMAIL_SUCESSO;
		} catch (Exception e) {
			e.printStackTrace();
			return ERRO_ENVIAR_EMAIL;
		}
	}
	
	public void setLeitura(Leitura leitura) {
		this.leitura = leitura;
	}

}
