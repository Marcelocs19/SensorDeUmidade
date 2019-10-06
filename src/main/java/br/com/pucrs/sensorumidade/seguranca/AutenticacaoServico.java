package br.com.pucrs.sensorumidade.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.pucrs.sensorumidade.modelo.Usuario;

public class AutenticacaoServico  implements UserDetailsService {
	
	@Autowired
	UsuarioPerfilRepositorio usuarioPerfilRepositorio;

	@Override
	public UserDetails loadUserByUsername(String nomeUsuario){
		Optional<Usuario> usuario = usuarioPerfilRepositorio.findByNome(nomeUsuario);
		if(usuario.isPresent())	{return usuario.get();}
		else {throw new UsernameNotFoundException("Dados Inválidos");}
	}


}
