package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="imagens")
public final class Imagem extends RestModel {

	@FormParam("caminho")
	private String caminho;
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho=caminho;
	}

	public Imagem(){}

	public Imagem(String id){
		this.id = Long.valueOf(id);
	}
}