package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Cliente;
import br.com.login.huntvision.ws.dao.ClienteDAO;
@Path("/clientes")
public class ClienteService extends RestService<Cliente> {

	@Override
	public void initDAO() {
		this.restDAO = new ClienteDAO();
	}

}
