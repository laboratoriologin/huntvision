package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;

import br.com.login.huntvision.ws.dao.ProtocolosAcoesDAO;
import br.com.login.huntvision.ws.model.ProtocoloAcao;

@Path("/protocolos_acoes")
public class ProtocolosAcoesService extends RestService<ProtocoloAcao> {

	@Override
	public void initDAO() {
		this.restDAO = new ProtocolosAcoesDAO();
	}

}
