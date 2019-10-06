package br.com.pucrs.sensorumidade.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(name = "NOME", nullable = false, length = 50, unique = true) 
	private String nome;
	
	@Email(message = "O campo e-mail não está corretamente preenchido.")
	@Column(name = "EMAIL", nullable = false)	
	private String email;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Sensor> sensores;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<UsuarioPerfil> usuarioPerfis = new ArrayList<>();
	
	public Usuario() {
		super();
	}

	public Usuario(@NotBlank(message = "O campo nome é obrigatório.") String nome,
			@Email(message = "O campo e-mail não está corretamente preenchido.") String email,
			@NotBlank(message = "O campo senha é obrigatório.") String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.usuarioPerfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}	

}
