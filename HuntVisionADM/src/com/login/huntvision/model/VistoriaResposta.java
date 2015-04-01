package com.login.huntvision.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.topsys.database.hibernate.TSActiveRecordAb;

@Entity
@Table(name = "vistorias_respostas")
public final class VistoriaResposta extends TSActiveRecordAb<VistoriaResposta> { 
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column(name = "imagem")
	private String imagem;
	
	/**
	 * @return the imagem
	 */
	public String getImagem() {
		return imagem;
	}

	/**
	 * @param imagem the imagem to set
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
	 * @param id the id to set
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
	 * @param resposta the resposta to set
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
	 * @param vistoria the vistoria to set
	 */
	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result
				+ ((resposta == null) ? 0 : resposta.hashCode());
		result = prime * result
				+ ((vistoria == null) ? 0 : vistoria.hashCode());
		return result;
	}

	/* (non-Javadoc)
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