package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Permissao;
import br.com.login.huntvision.ws.dao.PermissaoDAO;
@Path("/permissoes")
public class PermissaoService extends RestService<Permissao> {

	@Override
	public void initDAO() {
		this.restDAO = new PermissaoDAO();
	}

}
