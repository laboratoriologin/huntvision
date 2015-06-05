package com.login.huntvision.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.topsys.database.hibernate.TSActiveRecordAb;

@SuppressWarnings("serial")
@Entity
@Table(name = "protocolos_acoes")
public final class ProtocoloAcao extends TSActiveRecordAb<ProtocoloAcao> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "protocolo_id")
	private Protocolo protocolo;

	@ManyToOne
	@JoinColumn(name = "acao_id")
	private Acao acao;

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
	 * @return the protocolo
	 */
	public Protocolo getProtocolo() {
		return protocolo;
	}

	/**
	 * @param protocolo
	 *            the protocolo to set
	 */
	public void setProtocolo(Protocolo protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * @return the acao
	 */
	public Acao getAcao() {
		return acao;
	}

	/**
	 * @param acao
	 *            the acao to set
	 */
	public void setAcao(Acao acao) {
		this.acao = acao;
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
		ProtocoloAcao other = (ProtocoloAcao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
