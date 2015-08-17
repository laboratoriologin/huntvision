package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")
public class Item extends Base {

      @DatabaseField(id = true)
    private String id;

    @DatabaseField
    private String descricao;

    @DatabaseField
    private String chave;


    private String serviceName;

    public Item() {

        setServiceName("itens");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
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
}
