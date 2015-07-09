package br.com.login.huntvision.ws.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement(name ="agendas")
public final class Agenda extends RestModel {


	
	@FormParam("cliente_id")
	private Cliente cliente;

	@FormParam("usuario_id")
	private Usuario usuario;


	
	@FormParam("descricao")
	private String descricao;


	@FormParam("data_hora")
	private String dataHora;

	@FormParam("data_hora_chegada")
	private String dataHoraChegada;

	@FormParam("data_hora_saida")
	private String dataHoraSaida;
	
	@FormParam("data_hora_formatada")
	private String dataHoraFormatada;
	
	@FormParam("data_hora_chegada_formatada")
	private String dataHoraChegadaFormatada;
	
	@FormParam("data_hora_saida_formatada")
	private String dataHoraSaidaFormatada;
	
	
	

	public Agenda(){}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(String dataHoraChegada) {
		this.dataHoraChegada = dataHoraChegada;
	}

	public String getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(String dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public Agenda(String id){
		this.id = Long.valueOf(id);
	}

	
	public String getDataHoraFormatada() {
	
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
		try {
	
			return formatter.format(formatter.parse(dataHora));
	
		} catch (ParseException e) {
			
			return null;
		}
	}

	public void setDataHoraFormatada(String dataHoraFormatada) {
		this.dataHoraFormatada = dataHoraFormatada;
	}
	
	

	public String getDataHoraChegadaFormatada() {
		if(dataHoraChegada==null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
	
			return formatter.format(formatter.parse(dataHoraChegada));
	
		} catch (ParseException e) {
			
			return null;
		}
	}

	public void setDataHoraChegadaFormatada(String dataHoraChegadaFormatada) {
		this.dataHoraChegadaFormatada = dataHoraChegadaFormatada;
	}

	public String getDataHoraSaidaFormatada() {
		if(dataHoraSaida==null) {
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		
		try {
			
			return formatter.format(formatter.parse(dataHoraSaida));
	
		} catch (ParseException e) {
			
			return null;
		}
	}

	public void setDataHoraSaidaFormatada(String dataHoraSaidaFormatada) {
		this.dataHoraSaidaFormatada = dataHoraSaidaFormatada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((dataHoraChegada == null) ? 0 : dataHoraChegada.hashCode());
		result = prime * result + ((dataHoraSaida == null) ? 0 : dataHoraSaida.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (dataHoraChegada == null) {
			if (other.dataHoraChegada != null)
				return false;
		} else if (!dataHoraChegada.equals(other.dataHoraChegada))
			return false;
		if (dataHoraSaida == null) {
			if (other.dataHoraSaida != null)
				return false;
		} else if (!dataHoraSaida.equals(other.dataHoraSaida))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	
	
}