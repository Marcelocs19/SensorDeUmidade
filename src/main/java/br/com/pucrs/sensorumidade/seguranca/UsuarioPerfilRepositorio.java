package br.com.pucrs.sensorumidade.seguranca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.pucrs.sensorumidade.modelo.Usuario;

@Service
public interface UsuarioPerfilRepositorio extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNome(String nome);

}
