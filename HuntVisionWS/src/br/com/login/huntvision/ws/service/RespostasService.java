package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Resposta;
import br.com.login.huntvision.ws.dao.RespostasDAO;

@Path("/respostas")
public class RespostasService extends RestService<Resposta> {

	@Override
	public void initDAO() {
		this.restDAO = new RespostasDAO();
	}

}
