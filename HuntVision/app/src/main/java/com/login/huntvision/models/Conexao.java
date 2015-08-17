package com.login.huntvision.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ricardo on 19/01/2015.
 */
@SuppressWarnings("serial")
public class Conexao extends Base {


    private String id;


    private String url;


    private String serviceName;

    public Conexao() {

        setServiceName("conexoes");
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



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
