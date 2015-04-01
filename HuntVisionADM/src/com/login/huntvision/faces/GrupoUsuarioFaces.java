package com.login.huntvision.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.login.huntvision.model.GrupoUsuario;
import com.login.huntvision.model.Menu;
import com.login.huntvision.model.Permissao;
import com.login.huntvision.util.HuntVisionUtil;

import br.com.topsys.util.TSUtil;



@ViewScoped
@ManagedBean(name = "grupoUsuarioFaces")
public class GrupoUsuarioFaces extends CrudFaces<GrupoUsuario> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> comboMenus;
	private Permissao permissaoSelecionada;

	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboMenus = super.initCombo(new Menu().pesquisarExecutaveis(), "id", "descricao");
		setFieldOrdem("descricao");
	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}
	
	
	public String delPermissao() {
		getCrudModel().getPermissoes().remove(this.permissaoSelecionada);
		return null;
	}

	public String addPermissao() {

		Permissao permissao = new Permissao();

		permissao.setGrupoUsuario(getCrudModel());
		permissao.setMenu(new Menu());
		permissao.setFlagInserir(Boolean.TRUE);
		permissao.setFlagAlterar(Boolean.TRUE);
		permissao.setFlagExcluir(Boolean.FALSE);

		if (TSUtil.isEmpty(getCrudModel().getPermissoes())) {
			getCrudModel().setPermissoes(new ArrayList<Permissao>());
		}

		if (!this.getCrudModel().getPermissoes().contains(permissao)) {

			this.getCrudModel().getPermissoes().add(permissao);

		} else {

			HuntVisionUtil.addErrorMessage("Essa permissão já foi adicionada");
		}

		return null;
	}

	public List<SelectItem> getComboMenus() {
		return comboMenus;
	}

	public void setComboMenus(List<SelectItem> comboMenus) {
		this.comboMenus = comboMenus;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}
}
