package br.com.pucrs.sensorumidade.servico;

public class ServicoExcecao extends RuntimeException {

	private static final long serialVersionUID = 4244881451243023367L;

	public ServicoExcecao(String mensagem) {
		super(mensagem);
	}
	
	public ServicoExcecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }


}
