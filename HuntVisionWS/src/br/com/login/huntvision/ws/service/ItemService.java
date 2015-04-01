package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Item;
import br.com.login.huntvision.ws.dao.ItemDAO;
@Path("/itens")
public class ItemService extends RestService<Item> {

	@Override
	public void initDAO() {
		this.restDAO = new ItemDAO();
	}

}
