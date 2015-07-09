package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Questionario;
import br.com.login.huntvision.ws.dao.QuestionariosDAO;
@Path("/questionarios")
public class QuestionariosService extends RestService<Questionario> {

	@Override
	public void initDAO() {
		this.restDAO = new QuestionariosDAO();
	}

}
