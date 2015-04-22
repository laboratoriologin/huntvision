package br.com.login.huntvision.ws.model;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.resteasy.annotations.Form;

@SuppressWarnings("serial")
@XmlRootElement(name = "respostas")
public final class Vistoria extends RestModel {

	@FormParam("id")
	private Long id;

	@FormParam("data")
	private String data;

	@FormParam("latitude")
	private Double latitude;

	@FormParam("longitude")
	private Double longitude;

	@FormParam("cliente")
	private Cliente cliente;

	@FormParam("usuario")
	private Usuario usuario;

	@Form(prefix = "respostas")
	private List<VistoriaResposta> respostas;

	public Vistoria() {
	}

	public Vistoria(String id) {
		this.id = Long.valueOf(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<VistoriaResposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<VistoriaResposta> respostas) {
		this.respostas = respostas;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}