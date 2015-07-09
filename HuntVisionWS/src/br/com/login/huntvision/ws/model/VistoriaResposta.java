package br.com.login.huntvision.ws.model;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.Form;

@SuppressWarnings("serial")
@XmlRootElement(name = "vistorias_respostas")
public final class VistoriaResposta extends RestModel {

	@FormParam("id")
	private Long id;

	@FormParam("imagem")
	private String imagem;

	@FormParam("resposta")
	private Resposta resposta;

	@FormParam("vistoria")
	private Vistoria vistoria;

	@FormParam("observacao")
	private String observacao;

	@Form(prefix = "imagens")
	private List<Imagem> imagens;

	public VistoriaResposta() {
	}

	public VistoriaResposta(String id) {
		this.id = Long.valueOf(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Vistoria getVistoria() {
		return vistoria;
	}

	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao
	 *            the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

}