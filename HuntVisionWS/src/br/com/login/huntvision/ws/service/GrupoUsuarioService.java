package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.GrupoUsuario;
import br.com.login.huntvision.ws.dao.GrupoUsuarioDAO;
@Path("/grupos_usuarios")
public class GrupoUsuarioService extends RestService<GrupoUsuario> {

	@Override
	public void initDAO() {
		this.restDAO = new GrupoUsuarioDAO();
	}

}
