package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;


/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")
public class Protocolo extends Base {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private String norma;

    public Protocolo() {

        setServiceName("protocolos");
    }

    private String serviceName;


    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }
}
