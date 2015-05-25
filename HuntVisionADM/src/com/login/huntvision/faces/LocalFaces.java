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

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Cliente;

import com.login.huntvision.model.Item;
import com.login.huntvision.model.ItemLocal;
import com.login.huntvision.model.GrupoUsuario;
import com.login.huntvision.model.Local;
import com.login.huntvision.model.Resposta;
import com.login.huntvision.model.TipoQuestionario;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "localFaces")
public class LocalFaces extends CrudFaces<Local> {

	private static final long serialVersionUID = 1L;

	private List<SelectItem> comboCliente;
	private List<SelectItem> comboLocal;
	private List<String> selectedOptions = new ArrayList<String>();
	private List<SelectItem> comboItem;

	@PostConstruct
	protected void init() {
		this.clearFields();
		this.comboCliente = super.initCombo(new Cliente().findByModel("nome"),
				"id", "nome");
		
		
		getCrudModel().setCliente(new Cliente());

		setFieldOrdem("nomeLocal");
	}

	/**
	 * @return the comboLocal
	 */
	public List<SelectItem> getComboLocal() {
		return comboLocal;
	}

	/**
	 * @param comboLocal the comboLocal to set
	 */
	public void setComboLocal(List<SelectItem> comboLocal) {
		this.comboLocal = comboLocal;
	}

	@Override
	public String limpar() {

		super.limpar();
		this.getCrudModel().setItensLocais(new ArrayList<ItemLocal>());
		this.getCrudModel().setCliente(new Cliente());
		return null;

	}

	@Override
	protected String detail() {
		super.detail();
		
		this.selectedOptions = new ArrayList<String>();
		
		for (ItemLocal itemLocal : getCrudModel().getItensLocais()) {
			
			this.selectedOptions.add(itemLocal.getItem().getId().toString());
			
		}
		
		return null;
	}

	@Override
	protected void prePersist() {
		
	

		getCrudModel().setItensLocais(new ArrayList<ItemLocal>());

		ItemLocal itemLocal = null;

		for (String id : selectedOptions) {

			itemLocal = new ItemLocal();
			itemLocal.setLocal(getCrudModel());
			itemLocal.setItem(new Item(id));
			getCrudModel().getItensLocais().add(itemLocal);

		}

	}

	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the comboCliente
	 */
	public List<SelectItem> getComboCliente() {
		return comboCliente;
	}

	/**
	 * @param comboCliente
	 *            the comboCliente to set
	 */
	public void setComboCliente(List<SelectItem> comboCliente) {
		this.comboCliente = comboCliente;
	}

	public String adicionarItemLocal() {

		this.getCrudModel().getItensLocais().add(new ItemLocal());

		return null;
	}

	/**
	 * @return the selectedOptions
	 */
	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

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
