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
import javax.persistence.Convert;

import br.com.topsys.exception.TSApplicationException;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.Destinatario;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.ItemLocal;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "destinatarioFaces")
public class DestinatarioFaces extends CrudFaces<Destinatario> {
	
	private static long serialVersionUID = 1L;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the comboDestinatario
	 */
	public List<SelectItem> getComboDestinatario() {
		return comboDestinatario;
	}

	/**
	 * @param comboDestinatario the comboDestinatario to set
	 */
	public void setComboDestinatario(List<SelectItem> comboDestinatario) {
		this.comboDestinatario = comboDestinatario;
	}

	/**
	 * @return the selectedOptions
	 */
	public List<String> getSelectedOptions() {
		return selectedOptions;
	}

	/**
	 * @param selectedOptions the selectedOptions to set
	 */
	public void setSelectedOptions(List<String> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	private List<SelectItem> comboItemLocal;
	private List<SelectItem> comboDestinatario;
	private List<String> selectedOptions = new ArrayList<String>();
	
	@PostConstruct
	protected void init() {

		this.clearFields();
		
		this.comboItemLocal = super.initCombo(new ItemLocal().findAll(), "id", "comboText");
		
		this.comboDestinatario = super.initCombo(new Destinatario().findByModel("nome"), "id", "nome");

		setFieldOrdem("nome");
	}

	@Override
	public String limpar() {
		super.limpar();

		return null;
	}
	
	/**
	 * @return the comboItemLocal
	 */
	public List<SelectItem> getComboItemLocal() {
		return comboItemLocal;
	}

	/**
	 * @param comboItemLocal the comboItemLocal to set
	 */
	public void setComboItemLocal(List<SelectItem> comboItemLocal) {
		this.comboItemLocal = comboItemLocal;
	}

	
	@Override
	protected void prePersist() {
	
   
		if (this.selectedOptions != null){
		ItemLocal itemLocal = null;

		for (String id : selectedOptions) {

			
			itemLocal.setId(Long.parseLong(id));
			getCrudModel().setItemLocal(itemLocal);
	

		}
		
		}

	}


}
