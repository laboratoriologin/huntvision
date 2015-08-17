package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.login.huntvision.utils.Constantes;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")
public class Agenda extends Base {

   @DatabaseField(id = true)
    private String id;

    private Usuario usuario;

    @DatabaseField
    private String descricao;


    private Cliente cliente;

    @DatabaseField
    private Long clienteId;

    @DatabaseField
    private Long usuarioId;

    @DatabaseField
    private Date dataHora;

    @DatabaseField
    private Date dataHoraChegada;

    @DatabaseField
    private Date dataHoraSaida;


    private String serviceName;

    public Agenda() {

        setServiceName("agendas");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getStyleClass() {

        if (!Constantes.isEmpty(this.getDataHoraSaida())) {
            return "verde";
        }

        if (!Constantes.isEmpty(this.getDataHoraChegada())) {
            return "amarelo";
        }

        if (Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"), new Locale("pt","BR")).getTime().before(this.getDataHora())) {
            return null;
        }

        return "vermelho";

    }

    @Override
    public String toString() {
        return getCliente().getNome() + " | " + getUsuario().getNome();
    }


    public String getMapDot() {

        if (!Constantes.isEmpty(this.getDataHoraSaida())) {
            return Constantes.GREEN_DOT;
        }

        if (!Constantes.isEmpty(this.getDataHoraChegada())) {
            return  Constantes.YELLOW_DOT;
        }

        if (Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"), new Locale("pt", "BR")).getTime().before(this.getDataHora())) {
            return  Constantes.BLUE_DOT;
        }

        return  Constantes.RED_DOT;

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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
