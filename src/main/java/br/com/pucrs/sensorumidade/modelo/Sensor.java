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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "sensores")
public class Sensor extends EntidadeBase{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@NotBlank(message = "O campo mensagem é obrigatório.")
	@Column(name = "MENSAGEM", nullable = false)
	private String mensagem;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plantaId")
	private Planta planta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sensores")
	private Set<Leitor> leitor;
		
	public Sensor() {
		super();
	}

	public Sensor(@NotBlank(message = "O campo nome é obrigatório.") String nome,
			@NotBlank(message = "O campo mensagem é obrigatório.") String mensagem, Planta planta, Usuario usuario,
			Set<Leitor> leitor) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
		this.planta = planta;
		this.usuario = usuario;
		this.leitor = leitor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Leitor> getLeitor() {
		return leitor;
	}

	public void setLeitor(Set<Leitor> leitor) {
		this.leitor = leitor;
	}

	@Override
	public String toString() {
		return "Sensor [nome=" + nome + ", mensagem=" + mensagem + ", planta=" + planta + ", usuario=" + usuario
				+ ", leitor=" + leitor + ", isNew()=" + isNew() + "]";
	}
		
}
