package com.login.huntvision.model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import br.com.topsys.database.hibernate.TSActiveRecordAb;
import br.com.topsys.util.TSUtil;

@Entity
@Table(name = "vistorias_respostas")
public final class VistoriaResposta extends TSActiveRecordAb<VistoriaResposta> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "imagem")
	private String imagem;

	@Column(name = "observacao")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "local_id")
	private Local local;

	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(mappedBy = "vistoriaResposta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VistoriaRespostaImagem> imagens;

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

	/**
	 * @return the imagem
	 */
	public String getImagem() {
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@ManyToOne
	@JoinColumn(name = "resposta_id")
	private Resposta resposta;

	@ManyToOne
	@JoinColumn(name = "vistoria_id")
	private Vistoria vistoria;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the resposta
	 */
	public Resposta getResposta() {
		return resposta;
	}

	/**
	 * @param resposta
	 *            the resposta to set
	 */
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	/**
	 * @return the vistoria
	 */
	public Vistoria getVistoria() {
		return vistoria;
	}

	/**
	 * @param vistoria
	 *            the vistoria to set
	 */
	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	public List<VistoriaResposta> findAllByVistoria() {

		return find("from VistoriaResposta where vistoria.id = ?", null, vistoria.getId());

	}
	
	public List<VistoriaResposta> findAllByVistoriaNaoConformidade() {

		return find("from VistoriaResposta where resposta.flagNaoConformidade is true",  null, vistoria.getId());

	}

	public List<VistoriaResposta> findAllByVistoriaData() {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		return find("from VistoriaResposta where vistoria.cliente.id = ? and vistoria.id in (select id from Vistoria v where SUBSTRING(v.data, 7, 4) + SUBSTRING(v.data, 4, 2) + SUBSTRING(v.data, 1, 2) >= ? AND SUBSTRING(v.data, 7, 4) + SUBSTRING(v.data, 4, 2) + SUBSTRING(v.data, 1, 2) <= ?)", "id desc", vistoria.getCliente().getId(), format.format(vistoria.getDataInicial()), format.format(vistoria.getDataFinal()));

	}

	public List<VistoriaRespostaImagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<VistoriaRespostaImagem> imagens) {
		this.imagens = imagens;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result + ((vistoria == null) ? 0 : vistoria.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VistoriaResposta other = (VistoriaResposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		if (vistoria == null) {
			if (other.vistoria != null)
				return false;
		} else if (!vistoria.equals(other.vistoria))
			return false;
		return true;
	}

}