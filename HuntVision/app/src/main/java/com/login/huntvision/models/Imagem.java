package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")

public class Imagem implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField
    private String caminho;

    @DatabaseField
    private String vistoriaRespostaId;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getVistoriaRespostaId() {
        return vistoriaRespostaId;
    }

    public void setVistoriaRespostaId(String vistoriaRespostaId) {
        this.vistoriaRespostaId = vistoriaRespostaId;
    }
}
