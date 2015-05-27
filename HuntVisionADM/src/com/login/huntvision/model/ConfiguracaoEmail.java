package com.login.huntvision.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.topsys.database.hibernate.TSActiveRecordAb;

@Entity
@Table(name = "configuracoes_email")
public final class ConfiguracaoEmail extends TSActiveRecordAb<ConfiguracaoEmail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "remetente")
	private String remetente;

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	@Column(name = "smtp")
	private String smtp;

	@Column(name = "porta_smtp")
	private Integer portaSmtp;

	@Column(name = "senha")
	private String senha;

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public Integer getPortaSmtp() {
		return portaSmtp;
	}

	public void setPortaSmtp(Integer portaSmtp) {
		this.portaSmtp = portaSmtp;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}