package com.login.huntvision.faces;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

import com.login.huntvision.model.Menu;
import com.login.huntvision.model.Permissao;
import com.login.huntvision.model.Usuario;
import com.login.huntvision.util.Constantes;
import com.login.huntvision.util.EmailUtil;
import com.login.huntvision.util.GeneratePassword;
import com.login.huntvision.util.HuntVisionUtil;
import com.login.huntvision.util.UsuarioUtil;
import com.login.huntvision.util.Utilitarios;
import com.sun.mail.pop3.POP3SSLStore;

@SessionScoped
@ManagedBean(name = "autenticacaoFaces")
public class AutenticacaoFaces extends TSMainFaces {

	private String nomeTela;
	private String tela;
	private Usuario usuario;
	private Usuario usuarioLembrarSenha;
	private Usuario usuarioNovaSenha;
	private List<Menu> menus;
	private List<Permissao> permissoes;
	private Permissao PermissaoSelecionada;
	private Integer tabAtiva;

	public AutenticacaoFaces() throws TSApplicationException {

		clearFields();

		setTabAtiva(new Integer(0));

		setNomeTela("Área de Trabalho");
	}

	protected void clearFields() {

		this.usuario = new Usuario();

		this.usuarioLembrarSenha = new Usuario();

		this.usuarioNovaSenha = new Usuario();

		this.menus = Collections.emptyList();

		this.PermissaoSelecionada = new Permissao();

	}

	public String redirecionar() {

		if (!TSUtil.isEmpty(this.PermissaoSelecionada.getMenu().getManagedBeanReset())) {
			TSFacesUtil.removeManagedBeanInSession(this.PermissaoSelecionada.getMenu().getManagedBeanReset());
		}

		setTela(this.PermissaoSelecionada.getMenu().getUrl());
		setNomeTela("Área de Trabalho > " + PermissaoSelecionada.getMenu().getMenuPai().getDescricao() + " > " + PermissaoSelecionada.getMenu().getDescricao());
		setTabAtiva(Integer.valueOf(this.menus.indexOf(this.PermissaoSelecionada.getMenu().getMenuPai())));

		return SUCESSO;
	}

	private void carregarMenu() {

		menus = new Menu().pesquisarCabecalhos(UsuarioUtil.obterUsuarioConectado().getGrupoUsuario().getId());

		Permissao permissao = new Permissao();
		permissao.setGrupoUsuario(UsuarioUtil.obterUsuarioConectado().getGrupoUsuario());
		permissoes = permissao.pesquisarPermissoes();

	}

	public String login() {

		usuario.setSenha(Utilitarios.gerarHash(usuario.getSenha()));

		usuario = usuario.getByModel();

		if (TSUtil.isEmpty(usuario)) {
			clearFields();
			HuntVisionUtil.addWarnMessage("Login/Senha sem credencial para acesso.");
			return null;
		}

		TSFacesUtil.addObjectInSession(Constantes.USUARIO_CONECTADO, usuario);
		carregarMenu();

		return SUCESSO;
	}

	public String lembrarSenha() {

		usuarioLembrarSenha = usuarioLembrarSenha.getByModel();

		if (TSUtil.isEmpty(usuarioLembrarSenha)) {
			clearFields();
			HuntVisionUtil.addWarnMessage("Usuário inexistente.");
			return null;
		}

		try {

			String novaSenha = GeneratePassword.generatePassword();

			usuarioLembrarSenha.setSenha(Utilitarios.gerarHash(novaSenha));

			usuarioLembrarSenha.update();

			usuarioLembrarSenha.setSenha(novaSenha);

			EmailUtil.enviar(usuarioLembrarSenha.getEmail(), Utilitarios.getNovaSenhaEmailMessage(usuarioLembrarSenha));

			this.addInfoMessage("Uma nova senha foi enviada para seu e-mail");

			this.clearFields();

		} catch (TSApplicationException e) {
			this.addErrorMessageKey(e.getMessage());
		}

		return null;
	}

	public String alterarSenha() {

		if (!usuarioNovaSenha.getNovaSenha().equals(usuarioNovaSenha.getConfirmaSenha())) {
			this.addErrorMessage("As nova senha não confere com a confirmação de senha");
			return null;
		}

		usuarioNovaSenha.setSenha(Utilitarios.gerarHash(usuarioNovaSenha.getSenha()));

		String novaSenha = usuarioNovaSenha.getNovaSenha();

		usuarioNovaSenha = usuarioNovaSenha.getByModel();

		if (TSUtil.isEmpty(usuarioNovaSenha)) {
			clearFields();
			HuntVisionUtil.addWarnMessage("Usuário inválido.");
			return null;
		}

		try {

			usuarioNovaSenha.setSenha(Utilitarios.gerarHash(novaSenha));

			usuarioNovaSenha.update();

			this.addInfoMessage("Senha alterada com sucesso!");

			this.clearFields();

		} catch (TSApplicationException e) {
			this.addErrorMessageKey(e.getMessage());
		}

		return null;
	}

	public String logout() {
		TSFacesUtil.getRequest().getSession().invalidate();
		return "sair";
	}

	public String getNomeTela() {
		return nomeTela;
	}

	public void setNomeTela(String nomeTela) {
		this.nomeTela = nomeTela;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Integer getTabAtiva() {
		return tabAtiva;
	}

	public void setTabAtiva(Integer tabAtiva) {
		this.tabAtiva = tabAtiva;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public Permissao getPermissaoSelecionada() {
		return PermissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		PermissaoSelecionada = permissaoSelecionada;
	}

	public Usuario getUsuarioLembrarSenha() {
		return usuarioLembrarSenha;
	}

	public void setUsuarioLembrarSenha(Usuario usuarioLembrarSenha) {
		this.usuarioLembrarSenha = usuarioLembrarSenha;
	}

	public Usuario getUsuarioNovaSenha() {
		return usuarioNovaSenha;
	}

	public void setUsuarioNovaSenha(Usuario usuarioNovaSenha) {
		this.usuarioNovaSenha = usuarioNovaSenha;
	}
	
}
