package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;
import com.thoughtworks.xstream.XStream;

import org.apache.james.mime4j.field.datetime.DateTime;

import java.util.List;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Usuario  extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String login;

    @DatabaseField
    private String senha;

    @DatabaseField
    private Integer flagAtivo;

    @DatabaseField
    private String celular;

    @DatabaseField
    private String email;

    private String serviceName;

    public Usuario() {

        setServiceName("usuarios");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(Integer flagAtivo) {
        this.flagAtivo = flagAtivo;
    }


}
