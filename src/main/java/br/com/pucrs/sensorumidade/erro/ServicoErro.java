package br.com.pucrs.sensorumidade.erro;

public class ServicoErro extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicoErro(String mensagem) {
		super(mensagem);
	}

	public ServicoErro(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}	

}
