/**
 * 
 */
package com.login.huntvision.faces;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.login.huntvision.model.Destinatario;
import com.login.huntvision.model.ItemLocal;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "geradorDestinatarioFaces")
public class GeradorDestinatarioFaces extends CrudFaces<ItemLocal> {

	private List<SelectItem> comboItemLocal;
	private List<SelectItem> comboDestinatario;;

	@PostConstruct
	protected void init() {

		this.clearFields();

		this.comboItemLocal = super.initCombo(new ItemLocal().findAll(), "id", "comboText");

		this.comboDestinatario = super.initCombo(new Destinatario().findByModel("nome"), "id", "nome");

		setFieldOrdem("id");
	}


	public void onChangeEvent(AjaxBehaviorEvent event) {

		if (TSUtil.isEmpty(TSUtil.tratarLong(getCrudModel().getId()))) {

			this.clearFields();

		} else {

			this.detail();

		}

	}

	@Override
	protected void prePersist() {
		for (Destinatario objDestinatario : getCrudModel().getDestinatarios()) {
			objDestinatario.setItemLocal(getCrudModel());
		}
	}

	/**
	 * @return the comboDestinatario
	 */
	public List<SelectItem> getComboDestinatario() {
		return comboDestinatario;
	}

	/**
	 * @param comboDestinatario
	 *            the comboDestinatario to set
	 */
	public void setComboDestinatario(List<SelectItem> comboDestinatario) {
		this.comboDestinatario = comboDestinatario;
	}

	
	/**
	 * @return the comboItemLocal
	 */
	public List<SelectItem> getComboItemLocal() {
		return comboItemLocal;
	}

	/**
	 * @param comboItemLocal
	 *            the comboItemLocal to set
	 */
	public void setComboItemLocal(List<SelectItem> comboItemLocal) {
		this.comboItemLocal = comboItemLocal;
	}

	public String adicionarDestinatario() {

		this.getCrudModel().getDestinatarios().add(new Destinatario());

		return null;
	}

	public String removerDestinatario(Integer intPosition) {

		this.getCrudModel().getDestinatarios().remove(intPosition.intValue());

		return null;
	}

	@Override
	public String limpar() {
		super.limpar();
		this.getCrudModel().setDestinatarios(new ArrayList<Destinatario>());
		return null;

	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

}
