/**
 * 
 */
package com.login.huntvision.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.GrupoUsuario;
import com.login.huntvision.model.Usuario;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "clienteFaces")
public  class ClienteFaces extends CrudFaces<Cliente> {

	private static final long serialVersionUID = 1L;
		
	
	@PostConstruct
	protected void init() {
		this.clearFields();

		setFieldOrdem("nome");
	}



	
	
	@Override
	protected String detail() {
		super.detail();

		return null;
	}

	@Override
	protected void prePersist() {

	}
	
	@Override
	public String limparPesquisa() {
		String retorno = super.limparPesquisa();
		return retorno;
	}

}




