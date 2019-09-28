package br.com.pucrs.sensorumidade.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "tipos")
public class Tipo extends EntidadeBase{

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME")
	private String nome;
	
	@Min(0)
	@Max(100)
	@Column(name = "UMIDADEMINIMA", nullable = false)
	private int umidade_minima;

	@Min(0)
	@Max(100)
	@Column(name = "UMIDADEMAXIMA", nullable = false)
	private int umidade_maxima;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
	private Set<Planta> plantas;
	
		
	public Tipo() {
		super();
	}

	public Tipo(String nome, int umidade_minima, int umidade_maxima) {
		super();
		this.nome = nome;
		this.umidade_minima = umidade_minima;
		this.umidade_maxima = umidade_maxima;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getUmidade_minima() {
		return umidade_minima;
	}

	public void setUmidade_minima(int umidade_minima) {
		this.umidade_minima = umidade_minima;
	}

	public int getUmidade_maxima() {
		return umidade_maxima;
	}

	public void setUmidade_maxima(int umidade_maxima) {
		this.umidade_maxima = umidade_maxima;
	}

	public Set<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(Set<Planta> plantas) {
		this.plantas = plantas;
	}

	@Override
	public String toString() {
		return "Tipo [nome=" + nome + ", umidade_minima=" + umidade_minima + ", umidade_maxima=" + umidade_maxima
				+ ", plantas=" + plantas + ", isNew()=" + isNew() + "]";
	}
			
}
