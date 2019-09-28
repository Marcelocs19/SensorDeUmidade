package br.com.pucrs.sensorumidade.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pucrs.sensorumidade.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	List<Usuario> findAllByOrderByNomeAsc();
	
}
