/**
 * 
 */
package com.login.huntvision.faces;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.file.TSFile;
import br.com.topsys.web.faces.TSMainFaces;

import com.login.huntvision.model.Cliente;
import com.login.huntvision.model.ConfiguracaoEmail;
import com.login.huntvision.util.Constantes;

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

}
