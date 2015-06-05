package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;

import br.com.login.huntvision.ws.dao.ProtocoloDAO;
import br.com.login.huntvision.ws.model.Protocolo;

@Path("/protocolos")
public class ProtocoloService extends RestService<Protocolo> {

	@Override
	public void initDAO() {
		this.restDAO = new ProtocoloDAO();
	}

}
