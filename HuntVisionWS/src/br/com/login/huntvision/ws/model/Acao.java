package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="acao")
public final class Acao extends RestModel {

	@FormParam("nome")
	private String nome;

	


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	public Acao(){}

	public Acao(String id){
		this.id = Long.valueOf(id);
	}
}