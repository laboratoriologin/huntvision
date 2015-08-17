package com.login.huntvision.models;

/**
 * Created by Ricardo on 19/01/2015.
 */

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")

public class Vistoria extends Base {

    @DatabaseField(generatedId = true)
    private Long id;

    private String serviceName;

    @DatabaseField
    private String clienteId;

    @DatabaseField
    private String data;

    @DatabaseField
    private String usuarioId;

    @DatabaseField
    private String itemId;

    @DatabaseField
    private Integer pendenteImagem = 0;

    @DatabaseField
    private Double latitude = 0D;

    @DatabaseField
    private Double longitude = 0D;

    private Cliente cliente;

    @DatabaseField
    private Integer flagSincronizado;

    private Usuario usuario;

    private List<VistoriaResposta> respostas;

    public String getId() {
        return id.toString();
    }

    @Override
    public void setId(String id) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {return "vistorias";}

    @Override
    public void setServiceName(String serviceName) {

    }

    ;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
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

    public Integer getFlagSincronizado() {
        return flagSincronizado;
    }

    public void setFlagSincronizado(Integer flagSincronizado) {
        this.flagSincronizado = flagSincronizado;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<VistoriaResposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<VistoriaResposta> respostas) {
        this.respostas = respostas;
    }

    public Integer getPendenteImagem() {
        return pendenteImagem;
    }

    public void setPendenteImagem(Integer pendenteImagem) {
        this.pendenteImagem = pendenteImagem;
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
