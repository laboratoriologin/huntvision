/**
 * 
 */
package com.login.huntvision.faces;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.topsys.exception.TSApplicationException;

import com.login.huntvision.model.Destinatario;
import com.login.huntvision.model.Item;
import com.login.huntvision.model.ItemLocal;
import com.login.huntvision.model.Protocolo;
import com.login.huntvision.model.Questionario;
import com.login.huntvision.model.Resposta;
import com.login.huntvision.model.TipoQuestionario;
import com.login.huntvision.util.UsuarioUtil;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "geradorDestinatarioFaces")
public class GeradorDestinatarioFaces extends CrudFaces<ItemLocal> {

	private List<SelectItem> comboItemLocal;
	private List<SelectItem> comboDestinatario;
	private List<String> selectedOptions = new ArrayList<String>();
	private List<Destinatario> lstDestinatario = new ArrayList<Destinatario>();
	

	@PostConstruct
	protected void init() {

		this.clearFields();

		this.comboItemLocal = super.initCombo(new ItemLocal().findAll(), "id", "comboText");

		this.comboDestinatario = super.initCombo(new Destinatario().findByModel("nome"), "id", "nome");

		setFieldOrdem("nomeLocal");
	}
	
	@Override
	protected String insert() throws TSApplicationException {
		return super.update();// a tela eh sempre um update pois o local eh
								// selecionado no combo
	}

	@Override
	protected String detail() {
		
		super.detail();

		this.selectedOptions = new ArrayList<String>();

		for (Destinatario itemDestinatario : getCrudModel().getDestinatarios()) {

			this.selectedOptions.add(itemDestinatario.getId().toString());
		

		}

		return null;
	}



	@Override
	protected void prePersist() {
		 lstDestinatario = new ArrayList<Destinatario>();
		for (Destinatario objDestinatario : getCrudModel().getDestinatarios()) {
			objDestinatario.setItemLocal(getCrudModel());
			
			lstDestinatario.add(objDestinatario);
		}
		
		
		getCrudModel().setDestinatarios(lstDestinatario);

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
	
	
	public List<Destinatario> getLstDestinatario() {
		return lstDestinatario;
	}

	public void setLstDestinatario(List<Destinatario> lstDestinatario) {
		this.lstDestinatario = lstDestinatario;
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
