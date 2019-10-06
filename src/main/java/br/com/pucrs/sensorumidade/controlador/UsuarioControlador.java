package br.com.pucrs.sensorumidade.controlador;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.exceptions.TemplateInputException;

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
	private static final String REDIRECIONAR_USUARIO = "redirect:/usuarios";
	private static final String VISUALIZAR_BUSCAR_USUARIO = "usuarios/buscarUsuario";
	private static final String VISUALIZAR_NOVO_USUARIO = "usuarios/novoUsuario";
	private static final String VISUALIZAR_EDITAR_USUARIO = "usuarios/editarUsuario";
	private static final String VISUALIZAR_DETALHAR_USUARIO = "usuarios/usuarioDetalhar";
	private static final String DETALHAR_USUARIO = "/{id}/detalhar";
	private static final String EDITAR_USUARIO = "/{id}/editar";
	private static final String NOVO_USUARIO = "/novo";
	private static final String USUARIO = "usuario";

	/**
	 * Mensagens para o model
	 */
	private static final String MENSAGEM = "mensagem";
	private static final String MODELO_USUARIO = "usuarios";

	/**
	 * Mensagens de sucesso
	 */
	private static final String SALVAR_USUARIO_SUCESSO = "Usuario salvo com sucesso.";
	private static final String EDITAR_MENSAGEM_SUCESSO = "Usuario editado com sucesso.";

	/**
	 * Mensagens de erro
	 */
	private static final String ERRO_SALVAR_USUARIO = "Erro ao salvar o usuario.";
	private static final String ERRO_BUSCAR_USUARIO = "Erro ao buscar o(s) usuario(s).";
	private static final String ERRO_EDITAR_USUARIO = "Erro ao editar o usuario.";

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
	public ModelAndView buscarTodosUsuarios(Usuario usuario,RedirectAttributes atribRedirecionamento) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_BUSCAR_USUARIO);
			List<Usuario> listarUsuarios = usuarioServico.listarUsuarios();
			modelo.addObject(MODELO_USUARIO, listarUsuarios);
			return modelo;
		} catch (Exception excecao) {
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_BUSCAR_USUARIO);
			return new ModelAndView(VISUALIZAR_BUSCAR_USUARIO);
		}
	}

	@GetMapping(NOVO_USUARIO)
	public ModelAndView visualizarNovoUsuario(Usuario usuario) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_NOVO_USUARIO);
			modelo.addObject(MODELO_USUARIO, usuario);
			return modelo;
		} catch (Exception excecao) {
			ModelAndView erro = new ModelAndView(VISUALIZAR_NOVO_USUARIO);
			erro.addObject(MODELO_USUARIO, usuario);
			return erro;
		}

	}

	@PostMapping(NOVO_USUARIO)
	@Transactional
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
			ModelAndView erro = new ModelAndView(REDIRECIONAR_NOVO_USUARIO);
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_SALVAR_USUARIO);
			return erro;
		}
	}

	@GetMapping(EDITAR_USUARIO)
	public ModelAndView visualizarEditarUsuario(@PathVariable Long id) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_EDITAR_USUARIO);
			Usuario usuario = usuarioServico.buscarPorId(id);
			modelo.addObject(USUARIO, usuario);
			return modelo;
		} catch (TemplateInputException excecaoTemplate) {
			return new ModelAndView(VISUALIZAR_BUSCAR_USUARIO);
		} catch (Exception excecao) {
			return new ModelAndView(REDIRECIONAR_USUARIO);
		}
	}

	@PostMapping(EDITAR_USUARIO)
	public ModelAndView editarPerfil(@Valid Usuario usuario, BindingResult resultado,
			RedirectAttributes atribRedirecionamento) {
		try {
			if (!usuarioServico.validarSalvar(usuario, resultado)) {
				ModelAndView teste = new ModelAndView(VISUALIZAR_EDITAR_USUARIO);
				teste.addObject(usuario);
				return teste;
			} else {
				ModelAndView modelo = new ModelAndView(REDIRECIONAR_EDITAR_USUARIO);
				atribRedirecionamento.addFlashAttribute(MENSAGEM, EDITAR_MENSAGEM_SUCESSO);
				usuarioServico.salvar(usuario);
				return modelo;
			}
		} catch (Exception excecao) {
			ModelAndView erro = new ModelAndView(REDIRECIONAR_EDITAR_USUARIO);
			erro.addObject(MODELO_USUARIO, usuario);
			atribRedirecionamento.addFlashAttribute(ERRO_MENSAGEM_USUARIO, ERRO_EDITAR_USUARIO);
			return erro;
		}
	}

	@GetMapping(DETALHAR_USUARIO)
	public ModelAndView detalharUsuario(@PathVariable("id") Long id) {
		try {
			ModelAndView modelo = new ModelAndView(VISUALIZAR_DETALHAR_USUARIO);
			modelo.addObject(this.usuarioServico.buscarPorId(id));
			return modelo;
		} catch (TemplateInputException excecaoTemplate) {
			return new ModelAndView(VISUALIZAR_BUSCAR_USUARIO);
		}catch (Exception excecao) {
			return new ModelAndView(REDIRECIONAR_USUARIO);
		}
	}

}
