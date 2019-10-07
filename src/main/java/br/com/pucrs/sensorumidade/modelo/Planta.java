package br.com.pucrs.sensorumidade.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "plantas")
public class Planta extends EntidadeBase{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipoId")
	private Tipo tipo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Sensor> sensores;

	public Planta() {
		super();
	}

	public Planta(@NotBlank(message = "O campo nome é obrigatório.") String nome, Tipo tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}

	
	@Override
	public String toString() {
		return "Planta [nome=" + nome + ", tipo=" + tipo + ", sensores=" + sensores + ", isNew()=" + isNew() + "]";
	}
	

}
