package com.login.huntvision.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.login.huntvision.util.Constantes;

import br.com.topsys.database.hibernate.TSActiveRecordAb;
import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
@Entity
@Table(name = "agendas")
public final class Agenda extends TSActiveRecordAb<Agenda> {

	public Agenda() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "data_hora")
	private Date dataHora;

	@Column(name = "data_hora_chegada")
	private Date dataHoraChegada;

	@Column(name = "data_hora_saida")
	private Date dataHoraSaida;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return cliente.getNome() + " | " + usuario.getNome();
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getStyleClass() {

		if (!TSUtil.isEmpty(this.dataHoraSaida)) {
			return "verde";
		}

		if (!TSUtil.isEmpty(this.dataHoraChegada)) {
			return "amarelo";
		}

		if (Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"), new Locale("pt","BR")).getTime().before(this.dataHora)) {
			return null;
		}

		return "vermelho";

	}
	
	public String getMapDot() {

		if (!TSUtil.isEmpty(this.dataHoraSaida)) {
			return Constantes.GREEN_DOT;
		}

		if (!TSUtil.isEmpty(this.dataHoraChegada)) {
			return  Constantes.YELLOW_DOT;
		}

		if (Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"), new Locale("pt","BR")).getTime().before(this.dataHora)) {
			return  Constantes.BLUE_DOT;
		}

		return  Constantes.RED_DOT;

	}

	@Override
	public List<Agenda> findByModel(String... fieldsOrderBy) {
		
		Long usuarioId = TSUtil.isEmpty(this.usuario) ? null : TSUtil.tratarLong(this.usuario.getId());
		
		Long clienteId = TSUtil.isEmpty(this.cliente) ? null : TSUtil.tratarLong(this.cliente.getId());
		
		return find("From Agenda where cliente.id = coalesce(?, cliente.id) and usuario.id = coalesce(?,usuario.id)", null, clienteId, usuarioId);
	}
	
	public Date getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(Date dataHoraChegada) {
		this.dataHoraChegada = dataHoraChegada;
	}

	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

}
