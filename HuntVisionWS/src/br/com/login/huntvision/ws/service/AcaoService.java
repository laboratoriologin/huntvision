package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;

import br.com.login.huntvision.ws.dao.AcaoDAO;
import br.com.login.huntvision.ws.model.Acao;

@Path("/acoes")
public class AcaoService extends RestService<Acao> {

	@Override
	public void initDAO() {
		this.restDAO = new AcaoDAO();
	}

}
