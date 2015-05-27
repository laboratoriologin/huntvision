/**
 * 
 */
package com.login.huntvision.faces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.ConfiguracaoEmail;

/**
 * @author Ricardo
 *
 */
@ViewScoped
@ManagedBean(name = "configuracaoEmailFaces")
public class ConfiguracaoEmailFaces extends TSMainFaces {

	private static final long serialVersionUID = 1L;
	private ConfiguracaoEmail configuracaoEmail;

	@PostConstruct
	protected void init() {
		this.configuracaoEmail = new ConfiguracaoEmail().findAll().get(0);
		setClearFields(false);
	}

	@Override
	protected String update() throws TSApplicationException {

		this.configuracaoEmail.update();

		return null;

	}

	public ConfiguracaoEmail getConfiguracaoEmail() {
		return configuracaoEmail;
	}

	public void setConfiguracaoEmail(ConfiguracaoEmail configuracaoEmail) {
		this.configuracaoEmail = configuracaoEmail;
	}

}
