package com.login.huntvision.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;

import com.login.huntvision.model.GrupoUsuario;
import com.login.huntvision.model.Usuario;

@ViewScoped
@ManagedBean(name = "usuarioFaces")
public class UsuarioFaces extends CrudFaces<Usuario> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> comboGrupoUsuario;

	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboGrupoUsuario = super.initCombo(
				new GrupoUsuario().findByModel("descricao"), "id", "descricao");
		getCrudModel().setGrupoUsuario(new GrupoUsuario());
		setFieldOrdem("nome");
	}

	@Override
	protected String detail() {
		super.detail();

		return null;
	}


	@Override
	protected void preInsert() {
		super.preInsert();
		this.getCrudModel().setSenha(TSCryptoUtil.gerarHash(this.getCrudModel().getSenha(), TSConstant.CRIPTOGRAFIA_MD5));
	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

	public List<SelectItem> getComboGrupoUsuario() {
		return comboGrupoUsuario;
	}

	public void setComboGrupoUsuario(List<SelectItem> comboGrupoUsuario) {
		this.comboGrupoUsuario = comboGrupoUsuario;
	}

}
