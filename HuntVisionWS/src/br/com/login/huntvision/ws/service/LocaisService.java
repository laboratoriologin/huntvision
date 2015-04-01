package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Local;
import br.com.login.huntvision.ws.dao.LocaisDAO;
@Path("/locais")
public class LocaisService extends RestService<Local> {

	@Override
	public void initDAO() {
		this.restDAO = new LocaisDAO();
	}

}
