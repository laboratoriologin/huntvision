package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.TipoQuestionario;
import br.com.login.huntvision.ws.dao.TipoQuestionariosDAO;
@Path("/tipo_questionarios")
public class TipoQuestionariosService extends RestService<TipoQuestionario> {

	@Override
	public void initDAO() {
		this.restDAO = new TipoQuestionariosDAO();
	}

}
