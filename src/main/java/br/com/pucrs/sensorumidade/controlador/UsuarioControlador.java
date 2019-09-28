package br.com.pucrs.sensorumidade.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pucrs.sensorumidade.modelo.Usuario;
import br.com.pucrs.sensorumidade.servico.UsuarioServico;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

	/**
	 * Mapeamentos e Views
	 */
	private static final String REDIRECIONAR_NOVO_USUARIO = "redirect:/usuarios/novo";
	private static final String REDIRECIONAR_EDITAR_USUARIO = "redirect:/usuarios/{id}/editar";
	private static final String VISUALIZAR_NOVO_USUARIO = "usuarios/novoUsuario";
	private static final String VISUALIZAR_LISTAR_USUARIO = "usuarios/listarUsuario";
	private static final String NOVO_USUARIO = "/novo";

	/**
	 * Mensagens para o model
	 */
	private static final String MENSAGEM = "mensagem";
	private static final String MODELO_USUARIO = "usuarios";
	/**
	 * Mensagens de sucesso
	 */
	private static final String SALVAR_USUARIO_SUCESSO = "Usuario salvo com sucesso.";

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_SALVAR_USUARIO = "Erro ao salvar o usuario.";
	private static final String ERRO_LISTAR_PERFIL = "Erro ao listar o(s) usuario(s).";

	/**
	 * Mensagens de erro para o model
	 */
	private static final String ERRO_MENSAGEM_USUARIO = "mensagemError";

	private final UsuarioServico usuarioServico;

	public UsuarioControlador(UsuarioServico usuarioServico) {
		super();
		this.usuarioServico = usuarioServico;
	}

	@GetMapping
	public ModelAndView buscarTodosUsuarios(Usuario usuario, RedirectAttributes atribRedirecionamento) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_LISTAR_USUARIO);
			List<Usuario> listaUsuario = usuarioServico.listarUsuario(usuario);
			if (!listaUsuario.isEmpty()) {
				modelo.addObject(MODELO_USUARIO, listaUsuario);
				atribRedirecionamento.addFlashAttribute(MENSAGEM, ERRO_LISTAR_PERFIL);
			}
			return modelo;
		} catch (Exception excecao) {
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_LISTAR_PERFIL);
			return new ModelAndView(VISUALIZAR_LISTAR_USUARIO);
		}
	}

	@GetMapping(NOVO_USUARIO)
	public ModelAndView visualizarNovoUsuario(Usuario usuario) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_NOVO_USUARIO);
			modelo.addObject(MODELO_USUARIO, usuario);
			return modelo;
		} catch (Exception excecao) {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_NOVO_USUARIO);
			modelo.addObject(MODELO_USUARIO, usuario);
			return modelo;
		}

	}

	@PostMapping(NOVO_USUARIO)
	public ModelAndView criarNovoUsuario(@Valid Usuario usuario, BindingResult resultado,
			RedirectAttributes atribRedirecionamento) {
		try {
			if (!usuarioServico.validarSalvar(usuario, resultado)) {
				return visualizarNovoUsuario(usuario);
			} else {
				ModelAndView modelo = new ModelAndView(REDIRECIONAR_NOVO_USUARIO);
				usuarioServico.salvar(usuario);
				atribRedirecionamento.addFlashAttribute(MENSAGEM, SALVAR_USUARIO_SUCESSO);
				return modelo;
			}
		} catch (Exception excecao) {
			ModelAndView erroExcecao = new ModelAndView(REDIRECIONAR_NOVO_USUARIO);
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_SALVAR_USUARIO);
			return erroExcecao;

		}

	}
}
