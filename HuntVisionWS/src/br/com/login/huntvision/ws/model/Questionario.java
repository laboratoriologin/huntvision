package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@XmlRootElement(name = "questionarios")
public final class Questionario extends RestModel {

	@FormParam("data")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@FormParam("imagem")
	private Imagem imagem;

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	private List<Acao> acoes;

	public List<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	@FormParam("item")
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@FormParam("tipoquestionario")
	private TipoQuestionario tipoQuestionario;

	public TipoQuestionario getTipoQuestionario() {
		return tipoQuestionario;
	}

	public void setTipoQuestionario(TipoQuestionario tipoQuestionario) {
		this.tipoQuestionario = tipoQuestionario;
	}

	@FormParam("usuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@FormParam("pergunta")
	private String pergunta;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	@FormParam("status")
	private Boolean status;

	@FormParam("conformidade")
	private Boolean conformidade;

	public Boolean getConformidade() {
		return conformidade;
	}

	public void setConformidade(Boolean conformidade) {
		this.conformidade = conformidade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@FormParam("protocolo")
	private Protocolo protocolo;

	public Protocolo getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	public Questionario() {
	}

	public Questionario(String id) {
		this.id = Long.valueOf(id);
	}
}