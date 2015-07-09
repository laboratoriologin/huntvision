package br.com.login.huntvision.ws.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.com.login.huntvision.ws.dao.VistoriaDAO;
import br.com.login.huntvision.ws.exception.ApplicationException;
import br.com.login.huntvision.ws.model.Vistoria;
import br.com.login.huntvision.ws.util.Constantes;
import br.com.topsys.file.TSFile;

@Path("/vistorias")
public class VistoriaService extends RestService<Vistoria> {

	@Override
	public void initDAO() {
		this.restDAO = new VistoriaDAO();
	}

	@POST
	@Path("/imagens")
	@Consumes("multipart/form-data")
	public void insertImagens(MultipartFormDataInput input) throws ApplicationException {

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();

		InputStream inputStream;
		
		for (String imagem : uploadForm.keySet()) {
			
			try {
				
				inputStream = uploadForm.get(imagem).get(0).getBody(InputStream.class, null);

				TSFile.inputStreamToFile(inputStream, Constantes.CAMINHO_ARQUIVO + imagem);

			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}

	}

}
