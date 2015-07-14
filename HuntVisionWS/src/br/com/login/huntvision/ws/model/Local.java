package br.com.login.huntvision.ws.model;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name ="locais")
public final class Local extends RestModel {

	@FormParam("cliente")
	private Cliente cliente;

	@FormParam("nome_local")
	private String nomeLocal;
	
	public Local(){}

	public Local(String id){
		this.id = Long.valueOf(id);
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal=nomeLocal;
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
	}

	
}