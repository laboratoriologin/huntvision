package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
 

import br.com.login.huntvision.ws.dao.DestinariosDAO;
import br.com.login.huntvision.ws.model.Destinatario;

@Path("/destinatarios")
public class DestinatariosService extends RestService<Destinatario> {

	@Override
	public void initDAO() {
		this.restDAO = new DestinariosDAO();
	}

}
