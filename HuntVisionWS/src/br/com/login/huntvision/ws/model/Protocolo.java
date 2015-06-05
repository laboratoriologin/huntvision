package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="protocolo")
public final class Protocolo extends RestModel {

	@FormParam("nome")
	private String nome;


	@FormParam("norma")
	private String norma;


	/**
	 * @return the norma
	 */
	public String getNorma() {
		return norma;
	}

	/**
	 * @param norma the norma to set
	 */
	public void setNorma(String norma) {
		this.norma = norma;
	}

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


	public Protocolo(){}

	public Protocolo(String id){
		this.id = Long.valueOf(id);
	}
}