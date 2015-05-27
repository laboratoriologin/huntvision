/**
 * 
 */
package com.login.huntvision.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.login.huntvision.util.UsuarioUtil;

/**
 * @author Ricardo
 *
 */
@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "homeUsuarioFaces")
public class HomeUsuarioFaces extends HomeFaces {

	@PostConstruct
	public void init() {
		super.init();
	}

	@Override
	protected String find() {

		getVistoria().setUsuario(UsuarioUtil.obterUsuarioConectado());

		return super.find();

	}

}