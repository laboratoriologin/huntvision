package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="usuario")
public final class Usuario extends RestModel {

	@FormParam("celular")
	private String celular;

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular=celular;
	}

	@FormParam("email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	@FormParam("flagativo")
	private Boolean flagAtivo;

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo=flagAtivo;
	}

	@FormParam("grupousuario")
	private GrupoUsuario grupoUsuario;

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario=grupoUsuario;
	}

	@FormParam("login")
	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login=login;
	}

	@FormParam("nome")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome=nome;
	}

	@FormParam("senha")
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha=senha;
	}

	public Usuario(){}

	public Usuario(String id){
		this.id = Long.valueOf(id);
	}
}