package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@SuppressWarnings("serial")
@XmlRootElement(name ="questionarios")
public final class Questionario extends RestModel {

	@FormParam("data")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data=data;
	}

	@FormParam("imagem")
	private Imagem imagem;

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem=imagem;
	}

	@FormParam("item")
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item=item;
	}

	@FormParam("tipoquestionario")
	private TipoQuestionario tipoQuestionario;

	public TipoQuestionario getTipoQuestionario() {
		return tipoQuestionario;
	}

	public void setTipoQuestionario(TipoQuestionario tipoQuestionario) {
		this.tipoQuestionario=tipoQuestionario;
	}

	@FormParam("usuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario=usuario;
	}

	@FormParam("pergunta")
	private String pergunta;

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta=pergunta;
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
		this.status=status;
	}

	public Questionario(){}

	public Questionario(String id){
		this.id = Long.valueOf(id);
	}
}