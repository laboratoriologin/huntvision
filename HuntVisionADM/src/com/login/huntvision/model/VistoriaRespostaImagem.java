package com.login.huntvision.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.topsys.database.hibernate.TSActiveRecordAb;

@Entity
@Table(name = "vistorias_respostas_imagens")
public final class VistoriaRespostaImagem extends TSActiveRecordAb<VistoriaRespostaImagem> {

	private static final long serialVersionUID = 1L;
	
	

	public VistoriaRespostaImagem() {
		super();
	}

	public VistoriaRespostaImagem(VistoriaResposta vistoriaResposta) {
		super();
		this.vistoriaResposta = vistoriaResposta;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "caminho")
	private String caminho;

	@ManyToOne
	@JoinColumn(name = "vistoria_resposta_id")
	private VistoriaResposta vistoriaResposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public VistoriaResposta getVistoriaResposta() {
		return vistoriaResposta;
	}

	public void setVistoriaResposta(VistoriaResposta vistoriaResposta) {
		this.vistoriaResposta = vistoriaResposta;
	}

}