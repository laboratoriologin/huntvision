package com.login.huntvision.models;

import org.apache.http.NameValuePair;

import java.util.List;

import com.login.huntvision.network.http.InputStreamWrapper;

/**
 * Created by Ricardo on 19/01/2015.
 */
public class ServerRequest {

    public static final int POST = 1;
    public static final int PUT = 2;
    public static final int GET = 3;
    public static final int DELETE = 4;

    private int typeMethod;
    private String urlBase;
    private List<NameValuePair> params;
    private List<InputStreamWrapper> files;
    private Class clazz;

    public ServerRequest(int typeMethod, String urlBase, List<NameValuePair> params) {
        this.typeMethod = typeMethod;
        this.urlBase = urlBase;
        this.params = params;
    }

    public ServerRequest(int typeMethod, String urlBase, List<NameValuePair> params,List<InputStreamWrapper> files) {
        this.typeMethod = typeMethod;
        this.urlBase = urlBase;
        this.params = params;
        this.files = files;
    }

    public int getTypeMethod() {
        return typeMethod;
    }

    public void setTypeMethod(int typeMethod) {
        this.typeMethod = typeMethod;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }

    public List<InputStreamWrapper> getFiles() {
        return files;
    }
}
