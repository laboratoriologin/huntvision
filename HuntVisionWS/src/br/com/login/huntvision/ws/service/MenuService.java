package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Menu;
import br.com.login.huntvision.ws.dao.MenuDAO;
@Path("/menus")
public class MenuService extends RestService<Menu> {

	@Override
	public void initDAO() {
		this.restDAO = new MenuDAO();
	}

}
