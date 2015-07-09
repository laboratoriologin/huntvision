/**
 * 
 */
package com.login.huntvision.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.login.huntvision.model.Acao;
import com.login.huntvision.model.Protocolo;
import com.login.huntvision.model.ProtocoloAcao;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "protocoloFaces")
public class ProtocoloFaces extends CrudFaces<Protocolo> {

	/**
	 * @return the comboAcao
	 */
	public List<SelectItem> getComboAcao() {
		return comboAcao;
	}

	/**
	 * @param comboAcao
	 *            the comboAcao to set
	 */
	public void setComboAcao(List<SelectItem> comboAcao) {
		this.comboAcao = comboAcao;
	}

	private List<SelectItem> comboAcao;

	private List<String> selectedOptions = new ArrayList<String>();
	private List<SelectItem> comboItem;

	@PostConstruct
	protected void init() {

		this.clearFields();

		this.comboAcao = super.initCombo(new Acao().findByModel("nome"), "id", "nome");

		setFieldOrdem("nome");

	}

	@Override
	public String limpar() {

		super.limpar();
	
		this.getCrudModel().setAcoes(new ArrayList<ProtocoloAcao>());

		return null;

	}

	@Override
	protected String detail() {

		super.detail();

		this.selectedOptions = new ArrayList<String>();

		for (ProtocoloAcao itemAcao : getCrudModel().getAcoes()) {

			this.selectedOptions.add(itemAcao.getAcao().getId().toString());

		}

		return null;
	}

	@Override
	protected void prePersist() {

		getCrudModel().getAcoes().clear();

		ProtocoloAcao protocoloAcao = null;

		for (String id : selectedOptions) {

			protocoloAcao = new ProtocoloAcao();

			protocoloAcao.setAcao(new Acao());

			protocoloAcao.setProtocolo(getCrudModel());

			protocoloAcao.getAcao().setId(Long.valueOf(id));

			getCrudModel().getAcoes().add(protocoloAcao);

		}

	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

	/**
	 * @return the selectedOptions
	 */
	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

	/**
	 * @return the serialVersionUID
	 */
	/**
	 * @param selectedOptions
	 *            the selectedOptions to set
	 */
	public void setSelectedOptions(List<String> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	/**
	 * @return the comboItem
	 */
	public List<SelectItem> getComboItem() {
		return comboItem;
	}

	/**
	 * @param comboItem
	 *            the comboItem to set
	 */
	public void setComboItem(List<SelectItem> comboItem) {
		this.comboItem = comboItem;
	}

}
