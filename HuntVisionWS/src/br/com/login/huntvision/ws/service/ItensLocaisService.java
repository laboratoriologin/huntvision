package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.ItemLocal;
import br.com.login.huntvision.ws.dao.ItensLocaisDAO;
@Path("/itens_locais")
public class ItensLocaisService extends RestService<ItemLocal> {

	@Override
	public void initDAO() {
		this.restDAO = new ItensLocaisDAO();
	}

}
