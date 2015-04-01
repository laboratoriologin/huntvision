package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="respostas")
public final class Resposta extends RestModel {

	@FormParam("descricao")
	private String descricao;
	
	
	@FormParam("id_questionario")
	private Questionario questionario;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}

	@FormParam("flagrespostacerta")
	private Boolean flagrespostacerta;

	public Boolean getFlagrespostacerta() {
		return flagrespostacerta;
	}

	public void setFlagrespostacerta(Boolean flagrespostacerta) {
		this.flagrespostacerta=flagrespostacerta;
	}

	@FormParam("observacao")
	private String observacao;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao=observacao;
	}

	public Resposta(){}

	public Resposta(String id){
		this.id = Long.valueOf(id);
	}

	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
}