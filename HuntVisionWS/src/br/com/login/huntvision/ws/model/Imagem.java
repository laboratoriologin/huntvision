package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="imagens")
public final class Imagem extends RestModel {

	@FormParam("caminho")
	private String caminho;
	
	
	@FormParam("id_questionario")
	private String questionario;

	/**
	 * @return the questionario
	 */
	public String getQuestionario() {
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(String questionario) {
		this.questionario = questionario;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho=caminho;
	}

	@FormParam("legenda")
	private String legenda;

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda=legenda;
	}

	public Imagem(){}

	public Imagem(String id){
		this.id = Long.valueOf(id);
	}
}