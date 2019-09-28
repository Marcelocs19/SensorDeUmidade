package br.com.pucrs.sensorumidade.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "leitor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leitor extends EntidadeBase{
	
	private static final long serialVersionUID = 1L;
	
	@Min(0)
	@Max(100)
	@Column(name = "UMIDADEATUAL", nullable = false)
	private int umidadeAtual;
	

}
