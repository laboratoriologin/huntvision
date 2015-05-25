package com.login.huntvision.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

import br.com.topsys.database.hibernate.TSActiveRecordAb;

import java.util.Date;

@Entity
@Table(name = "questionarios")
public final class Questionario extends TSActiveRecordAb<Questionario> { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data")
	private String data;


	@ManyToOne
	@JoinColumn(name = "id_item")
	private Item item;
	


	
	
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(mappedBy = "questionario", cascade = CascadeType.ALL)
	private List<Resposta> respostas;

	@ManyToOne
	@JoinColumn(name = "id_tipo_questionario")
	private TipoQuestionario tipoQuestionario;
	

	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	@Column(name = "pergunta")
	private String pergunta;

	@Column(name = "status")
	private Boolean status;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data=data;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}


	
	/**
	 * @return the tipoQuestionario
	 */
	public TipoQuestionario getTipoQuestionario() {
		return tipoQuestionario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @param tipoQuestionario the tipoQuestionario to set
	 */
	public void setTipoQuestionario(TipoQuestionario tipoQuestionario) {
		this.tipoQuestionario = tipoQuestionario;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta=pergunta;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((pergunta == null) ? 0 : pergunta.hashCode());
		result = prime * result
				+ ((respostas == null) ? 0 : respostas.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((tipoQuestionario == null) ? 0 : tipoQuestionario.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Questionario other = (Questionario) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
				if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (pergunta == null) {
			if (other.pergunta != null)
				return false;
		} else if (!pergunta.equals(other.pergunta))
			return false;
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipoQuestionario == null) {
			if (other.tipoQuestionario != null)
				return false;
		} else if (!tipoQuestionario.equals(other.tipoQuestionario))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
}
