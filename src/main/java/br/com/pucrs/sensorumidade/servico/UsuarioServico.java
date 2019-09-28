package br.com.pucrs.sensorumidade.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import br.com.pucrs.sensorumidade.modelo.Usuario;
import br.com.pucrs.sensorumidade.repositorio.UsuarioRepositorio;

public class UsuarioServico {

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_SALVAR_USUARIO = "Erro ao salvar o usuario.";
	private static final String ERRO_BUSCAR_USUARIO = "Erro ao buscar o(s) usuario(s).";
	private static final String ERRO_EDITAR_USUARIO = "Erro usuario não encontrado.";
	private static final String ERRO_BUSCAR_ID = "Erro ao buscar o usuario pelo id.";

	/**
	 * Mensagens de erro para o model
	 */
	private static final String ERRO_MENSAGEM_USUARIO = "mensagemError";

	private final UsuarioRepositorio usuarioRepositorio;

	public UsuarioServico(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	public List<Usuario> listarUsuario(Usuario usuario) {
		try {
			List<Usuario> listaUsuario;
			listaUsuario = usuarioRepositorio.findAllByOrderByNomeAsc();
			return listaUsuario;
		} catch (Exception excecao) {
			throw new ServicoExcecao(ERRO_BUSCAR_USUARIO, excecao);
		}
	}

	public void salvar(Usuario usuario) {
		try {
			this.usuarioRepositorio.saveAndFlush(usuario);
		} catch (Exception excecao) {
			throw new ServicoExcecao(ERRO_SALVAR_USUARIO, excecao);
		}

	}

	public boolean validarSalvar(Usuario usuario, BindingResult resultado) {
		try {
			boolean validar = true;
			if (resultado.hasErrors()) {
				validar = false;
			}
			return validar;
		} catch (Exception excecao) {
			throw new ServicoExcecao(ERRO_SALVAR_USUARIO, excecao);
		}
	}
	
	public Usuario buscarPorId(Long id) {
		try {
			Optional<Usuario> usuario = usuarioRepositorio.findById(id);
			if (usuario.isPresent()) {
				return usuario.get();
			} else {
				throw new ServicoExcecao(ERRO_EDITAR_USUARIO);
			}
		} catch (IllegalArgumentException excecao) {
			throw new ServicoExcecao(ERRO_BUSCAR_ID, excecao);
		} catch (Exception excecao) {
			throw new ServicoExcecao(ERRO_BUSCAR_ID);
		}

	}

}
