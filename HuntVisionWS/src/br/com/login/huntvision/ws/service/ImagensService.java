package br.com.login.huntvision.ws.service;

import javax.ws.rs.Path;
import br.com.login.huntvision.ws.model.Imagem;
import br.com.login.huntvision.ws.dao.ImagensDAO;
@Path("/imagens")
public class ImagensService extends RestService<Imagem> {

	@Override
	public void initDAO() {
		this.restDAO = new ImagensDAO();
	}

}
