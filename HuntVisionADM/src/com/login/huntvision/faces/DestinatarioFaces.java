/**
 * 
 */
package com.login.huntvision.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.login.huntvision.model.Destinatario;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "destinatarioFaces")
public class DestinatarioFaces extends CrudFaces<Destinatario> {
	
	
	
	
	@PostConstruct
	protected void init() {

		this.clearFields();
		
		setFieldOrdem("nome");
	}

	@Override
	public String limpar() {
		super.limpar();

		return null;
	}
	

	


}
