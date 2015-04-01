/**
 * 
 */
package com.login.huntvision.faces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Cliente;


import com.login.huntvision.model.ItemLocal;
import com.login.huntvision.model.GrupoUsuario;
import com.login.huntvision.model.Local;
import com.login.huntvision.model.Questionario;
import com.login.huntvision.model.TipoQuestionario;


/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "tipoQuestionarioFaces")
public  class TipoQuestionarioFaces extends CrudFaces<TipoQuestionario> {

	private static final long serialVersionUID = 1L;

	
	@PostConstruct
	protected void init() {
		this.clearFields();
	
			setFieldOrdem("descricao");
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}




